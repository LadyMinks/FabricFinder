package com.example.fabricfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author Minka Firth
 */
class FabricViewModel(private val dao: FabricDao) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FABRIC_COLOUR)
    private val _fabrics = _sortType
            .flatMapLatest { sortType ->
                when(sortType){
                    SortType.FABRIC_LENGTH -> dao.getFabricOrderedByLength()
                    SortType.FABRIC_WIDTH -> dao.getFabricOrderedByWidth()
                    SortType.FABRIC_COLOUR -> dao.getFabricOrderedByColour()
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(FabricState())
    val state = combine(_state, _sortType, _fabrics) {state, sortType, fabrics ->
        state.copy(
                fabrics = fabrics,
                sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FabricState())

    fun onEvent(event: FabricEvent) {
        when (event) {
            is FabricEvent.DeleteFabric -> {
                viewModelScope.launch {
                    dao.deleteFabric(event.fabric)
                }
            }

            FabricEvent.HideDialog -> {
                _state.update {
                    it.copy(
                            isAddingFabric = false
                    )
                }
            }

            FabricEvent.SaveFabric -> {
                val fabricColour = state.value.fabricColour
                val fabricLength = state.value.fabricLength
                val fabricWidth = state.value.fabricWidth

                if (fabricColour.isBlank() || fabricLength == 0 || fabricWidth == 0 ){
                    return
                }

                val fabric = Fabric(
                        length = fabricLength,
                        width = fabricWidth,
                        colour = fabricColour
                )

                viewModelScope.launch{
                    dao.upsertFabric(fabric)
                }

                _state.update{it.copy(
                        isAddingFabric = false
                )}

            }

            is FabricEvent.SetFabricColour -> {
                _state.update {
                    it.copy(
                            fabricColour = event.fabricColour
                    )
                }
            }

            is FabricEvent.SetFabricLength -> {
                _state.update {
                    it.copy(
                            fabricLength = event.fabricLength
                    )
                }
            }

            is FabricEvent.SetFabricWidth -> {
                _state.update {
                    it.copy(
                            fabricWidth = event.fabricWidth
                    )
                }
            }

            FabricEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                            isAddingFabric = true
                    )
                }
            }

            is FabricEvent.SortFabrics -> {
                _sortType.value = event.sortType
            }
        }
    }
}