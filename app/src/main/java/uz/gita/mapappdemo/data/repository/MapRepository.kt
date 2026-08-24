package uz.gita.mapappdemo.data.repository

import uz.gita.mapappdemo.data.model.RestaurantMapDto

interface MapRepository {
    suspend fun getRestaurantsForMap(): Result<List<RestaurantMapDto>>
}