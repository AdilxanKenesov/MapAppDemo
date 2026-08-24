package uz.gita.mapappdemo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.gita.mapappdemo.data.repository.MapRepositoryImpl

@Suppress("UNCHECKED_CAST")
class MapViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(MapRepositoryImpl.getInstance()) as T
    }
}