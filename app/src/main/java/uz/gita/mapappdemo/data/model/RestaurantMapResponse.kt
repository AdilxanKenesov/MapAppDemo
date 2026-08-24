package uz.gita.mapappdemo.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantMapResponse(
    val success: Boolean = false,
     val data: List<RestaurantMapDto> = emptyList()
)
