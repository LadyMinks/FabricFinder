package com.example.fabricfinder

/**
 * @author Minka Firth
 */
sealed interface FabricEvent {

    object SaveFabric: FabricEvent
    data class SetFabricWidth(val fabricWidth: Int): FabricEvent
    data class SetFabricLength(val fabricLength: Int): FabricEvent
    data class SetFabricColour(val fabricColour: String): FabricEvent

    object ShowDialog: FabricEvent
    object HideDialog: FabricEvent
    data class SortFabrics(val sortType: SortType): FabricEvent
    data class DeleteFabric(val fabric: Fabric): FabricEvent



}