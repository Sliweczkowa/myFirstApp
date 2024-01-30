package com.example.helloworld.visuals.Edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.domain.model.InvalidPlaceException
import com.example.helloworld.domain.model.Place
import com.example.helloworld.domain.useCase.PlaceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val placeUseCases: PlaceUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentPlaceId: Int? = null

    private val _name = mutableStateOf(ModifyPlaceState())
    val name: State<ModifyPlaceState> = _name

    private val _date = mutableStateOf(ModifyPlaceState())
    val date: State<ModifyPlaceState> = _date

    private val _rating = mutableStateOf(ModifyPlaceState())
    val rating: State<ModifyPlaceState> = _rating

    private val _description = mutableStateOf(ModifyPlaceState())
    val description: State<ModifyPlaceState> = _description

    private val _photoPath = mutableStateOf(ModifyPlaceState())
    val photoPath: State<ModifyPlaceState> = _photoPath

    private val _eventFlow = MutableSharedFlow<UiEvent>()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SavePlace: UiEvent()
    }

    init {
        savedStateHandle.get<Int>("placeId")?.let { placeId ->
            if(placeId != -1) {
                viewModelScope.launch {
                    placeUseCases.getPlaceById(placeId)?.also { place ->
                        currentPlaceId = place.placeId
                        _name.value = name.value.copy(
                            name = place.placeName
                        )
                        _date.value = date.value.copy(
                            date = place.date
                        )
                        _rating.value = rating.value.copy(
                            rating = place.rating
                        )
                        _description.value = description.value.copy(
                            description = place.description
                        )
                        _photoPath.value = photoPath.value.copy(
                            photoPath = place.photoPath
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ModifyPlaceEvent) {
        when(event) {
            is ModifyPlaceEvent.EnteredName -> {
                _name.value = name.value.copy(
                    name = event.value
                )
            }
            is ModifyPlaceEvent.EnteredDate -> {
                _date.value = date.value.copy(
                    date = event.value
                )
            }
            is ModifyPlaceEvent.EnteredDescription -> {
                _description.value = description.value.copy(
                    description = event.value
                )
            }
            is ModifyPlaceEvent.EnteredRating -> {
                _rating.value = rating.value.copy(
                    rating = event.value
                )
            }
            is ModifyPlaceEvent.EnteredPhotoPath -> {
                _photoPath.value = photoPath.value.copy(
                    photoPath = event.value
                )
            }
            is ModifyPlaceEvent.SavePlace -> {
                viewModelScope.launch {
                    try {
                        placeUseCases.insertPlace(
                            Place(
                                placeId = currentPlaceId,
                                placeName = name.value.name,
                                date = date.value.date,
                                rating = rating.value.rating,
                                description = description.value.description,
                                photoPath = photoPath.value.photoPath
                            )
                        )
                        _eventFlow.emit(UiEvent.SavePlace)
                    } catch(e: InvalidPlaceException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save place"
                            )
                        )
                    }
                }
            }
        }
    }

//    fun onNameChange(name: String) {
//        this.name = name
//    }
//    fun onDateChange(date: String) {
//        this.date = date
//    }
//    fun onRatingChange(rating: Int) {
//        this.rating = rating
//    }
//    fun onDescriptionChange(description: String) {
//        this.description = description
//    }
//    fun onPhotoPathChange(photoPath: String) {
//        this.photoPath = photoPath
//    }
}
