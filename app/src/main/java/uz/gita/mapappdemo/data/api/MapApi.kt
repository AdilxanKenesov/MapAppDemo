package uz.gita.mapappdemo.data.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.mapappdemo.data.model.RestaurantMapResponse

interface MapApi {
    @GET("api/restaurants/map")
    suspend fun getRestaurantsForMap(): Response<RestaurantMapResponse>
}