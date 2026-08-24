package uz.gita.mapappdemo.presenter.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import uz.gita.mapappdemo.data.model.RestaurantMapDto

data class RestaurantClusterItem(
    val restaurant: RestaurantMapDto,
    val itemPosition: LatLng

): ClusterItem {
    override val position: LatLng = itemPosition
    override val title: String? = restaurant.name
    override val snippet: String? = restaurant.brandName
    override val zIndex: Float = 0f

}
