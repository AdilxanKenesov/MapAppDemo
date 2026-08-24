package uz.gita.mapappdemo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uz.gita.mapappdemo.presenter.MapScreen
import uz.gita.mapappdemo.ui.theme.MapAppDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MapAppDemoTheme {
                MapScreen()
            }
        }
    }
}



