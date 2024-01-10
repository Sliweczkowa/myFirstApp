package com.example.helloworld.visuals.Edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.helloworld.data.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val placeRepo: PlaceRepo) : ViewModel() {

        var name by mutableStateOf("")
        private set
        var date by mutableStateOf("")
        private set
        var rating by mutableStateOf("")
        private set
        var description by mutableStateOf("")
        private set
        var photoPath by mutableStateOf("")
        private set

        fun onNameChange(name: String) {
            this.name = name
        }
        fun onDateChange(date: String) {
            this.date = date
        }
        fun onratingChange(rating: String) {
            this.rating = rating
        }
        fun ondDscriptionChange(description: String) {
            this.description = description
        }
        fun onPhotoPathChange(photoPath: String) {
            this.photoPath = photoPath
        }

}
