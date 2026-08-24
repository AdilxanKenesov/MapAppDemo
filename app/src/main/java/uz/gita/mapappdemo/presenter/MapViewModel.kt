package uz.gita.mapappdemo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.mapappdemo.data.repository.MapRepository

class MapViewModel(
    private val repository: MapRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    init {
        loadRestaurants()
    }

    fun loadRestaurants() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.getRestaurantsForMap()
                .onSuccess { list ->

                    _uiState.update { it.copy(isLoading = false, restaurants = list) }
                }
                .onFailure { throwable ->
                    _uiState.update {

                        it.copy(isLoading = false, error = throwable.message ?: "error")
                    }
                }
        }
    }
}