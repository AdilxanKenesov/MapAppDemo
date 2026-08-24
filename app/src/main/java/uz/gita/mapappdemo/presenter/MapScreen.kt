package uz.gita.mapappdemo.presenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import uz.gita.mapappdemo.data.model.RestaurantMapDto
import uz.gita.mapappdemo.presenter.model.RestaurantClusterItem

@Composable
fun MapScreen() {
    val viewModel: MapViewModel = viewModel(factory = MapViewModelFactory())
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    MapContent(
        isLoading = uiState.isLoading,
        restaurants = uiState.restaurants,
        error = uiState.error
    )
}

@OptIn(ExperimentalMaterial3Api::class, MapsComposeExperimentalApi::class)
@Composable
private fun MapContent(
    isLoading: Boolean,
    restaurants: List<RestaurantMapDto>,
    error: String?
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(42.459813, 59.615938), 11f)
    }

    var selected by remember { mutableStateOf<RestaurantMapDto?>(null) }
    val dialogState = rememberModalBottomSheetState()

    val clusterItems = remember(restaurants) {
        restaurants.mapNotNull { restaurant ->
            val lat = restaurant.longitude
            val lng = restaurant.latitude
            if (lat != null && lng != null) {
                RestaurantClusterItem(restaurant, LatLng(lat, lng))
            } else {
                null
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(mapToolbarEnabled = false)
        ) {
            Clustering(
                items = clusterItems,
                onClusterItemClick = { item ->
                    selected = item.restaurant
                    true
                }
            )
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (error != null) {
            Text(text = error, modifier = Modifier.align(Alignment.Center))
        }

        selected?.let { restaurant->
            ModalBottomSheet(
                onDismissRequest = { selected = null },
                sheetState = dialogState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 32.dp)
                ){
                    Text(
                        text = restaurant.name.orEmpty(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = restaurant.brandName.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = restaurant.averageRating.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
