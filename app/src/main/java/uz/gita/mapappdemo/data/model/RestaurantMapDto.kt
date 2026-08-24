package uz.gita.mapappdemo.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class RestaurantMapDto(
  val id: Int = 0,
  val name: String? = null,
   @SerializedName("brand_name") val brandName: String? = null,
   @SerializedName("latitude") val latitude: Double? = null,
   @SerializedName("longitude") val longitude: Double? = null,
   @SerializedName("average_rating") val averageRating: Double = 0.0
)
