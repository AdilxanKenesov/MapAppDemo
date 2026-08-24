package uz.gita.mapappdemo.presenter

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapPolylineScreen() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(42.459813, 59.615938), 13f)
    }
    val context = LocalContext.current
    val hasLocation = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    val mapProperties = MapProperties(
        mapType = MapType.HYBRID,
        isTrafficEnabled = true,
        isMyLocationEnabled = hasLocation,
        isBuildingEnabled = true
    )

    val uiSettings = remember{
        MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = true,
            compassEnabled = true,
            mapToolbarEnabled = true,
            rotationGesturesEnabled = true,
            scrollGesturesEnabled = true,
            tiltGesturesEnabled = true,
            zoomGesturesEnabled = true,
            scrollGesturesEnabledDuringRotateOrZoom = true
        )

    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings
    ) {
        Polyline(
            points = listOf(
                LatLng(42.459813, 59.615938),
                LatLng(42.470000, 59.625000),
                LatLng(42.480000, 59.610000)
            ),
            color = Color.Red,
            width = 10f
        )
        Circle(
            center = LatLng(42.459813, 59.615938),
            radius = 100.0,
            fillColor = Color.Blue.copy(alpha = 0.2f),
            strokeColor = Color.Blue,
            strokeWidth = 2f
        )
        MarkerComposable(
            state = rememberMarkerState(position = LatLng(42.459813, 59.615938))
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Text("AAA", modifier = Modifier.padding(8.dp), color = Color.White)
            }
        }
//        GroundOverlay(
//            position = GroundOverlayPosition.create(
//                LatLng(42.459813, 59.615938),
//                200f,
//                200f
//            ),
////            image = BitmapDescriptor()
//        )


    }
}