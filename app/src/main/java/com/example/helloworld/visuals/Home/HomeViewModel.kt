package com.example.helloworld.visuals.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.domain.model.Place
import com.example.helloworld.domain.useCase.PlaceUseCases
import com.example.helloworld.domain.util.OrderType
import com.example.helloworld.domain.util.PlaceOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val placeUseCases: PlaceUseCases
) : ViewModel() {

    private val _state = mutableStateOf(PlaceState())
    val state: State<PlaceState> = _state

    private var recentlyDeletedPlace: Place? = null

    private var getPlacesJob: Job? = null

    init {
        getPlaces(PlaceOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: PlaceEvent) {
        when(event) {
            is PlaceEvent.Order -> {
                if(state.value.placeOrder::class == event.placeOrder::class &&
                    state.value.placeOrder.orderType == event.placeOrder.orderType
                    ) {
                    return
                }
                else {
                    getPlaces(event.placeOrder)
                }
            }
            is PlaceEvent.DeletePlace -> {
                viewModelScope.launch {
                    recentlyDeletedPlace = event.place
                    placeUseCases.deletePlace(event.place)
                }
            }
            is PlaceEvent.RestorePlace -> {
                viewModelScope.launch {
                    placeUseCases.insertPlace(recentlyDeletedPlace ?: return@launch)
                    recentlyDeletedPlace = null
                }
            }
            is PlaceEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getPlaces(placeOrder: PlaceOrder) {
        getPlacesJob?.cancel()
        getPlacesJob = placeUseCases.getPlaces(placeOrder)
            .onEach { places ->
                _state.value = state.value.copy(
                    places = places,
                    placeOrder = placeOrder)
            }
            .launchIn(viewModelScope)
    }
}
