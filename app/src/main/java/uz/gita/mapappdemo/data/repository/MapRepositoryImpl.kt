package uz.gita.mapappdemo.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.mapappdemo.data.ApiClient
import uz.gita.mapappdemo.data.api.MapApi
import uz.gita.mapappdemo.data.model.RestaurantMapDto

class MapRepositoryImpl (private val api: MapApi): MapRepository {
    companion object {
        @Volatile
        private var instance: MapRepository? = null

        fun getInstance(api: MapApi = ApiClient.mapApi): MapRepository =
            instance ?: synchronized(this) {
                instance ?: MapRepositoryImpl(api).also { instance = it }
            }
    }


    override suspend fun getRestaurantsForMap(): Result<List<RestaurantMapDto>> =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = api.getRestaurantsForMap()
                val body = response.body()
                if (!response.isSuccessful || body == null) {
                    throw Exception("error: ${response.code()}: ${response.errorBody()?.string() ?: response.message()}")
                }
                body.data
            }
        }


}