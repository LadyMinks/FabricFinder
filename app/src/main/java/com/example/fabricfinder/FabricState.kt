package com.example.fabricfinder

/**
 * @author Minka Firth
 */
data class FabricState(

        val fabrics: List<Fabric> = emptyList(),
        val fabricLength: Int = 0,
        val fabricWidth: Int = 0,
        val fabricColour: String = "",
        val isAddingFabric: Boolean = false,
        val sortType: SortType = SortType.FABRIC_COLOUR
)
