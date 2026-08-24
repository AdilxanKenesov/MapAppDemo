package uz.gita.mapappdemo.presenter

import uz.gita.mapappdemo.data.model.RestaurantMapDto

data class MapUiState(
    val isLoading: Boolean = false,
    val restaurants: List<RestaurantMapDto> = emptyList(),
    val error: String? = null
)
