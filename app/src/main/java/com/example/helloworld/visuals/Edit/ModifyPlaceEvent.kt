package com.example.helloworld.visuals.edit

sealed class ModifyPlaceEvent {
    data class EnteredName(val value: String): ModifyPlaceEvent()
    data class EnteredDate(val value: String): ModifyPlaceEvent()
    data class EnteredRating(val value: Int): ModifyPlaceEvent()
    data class EnteredDescription(val value: String): ModifyPlaceEvent()
    data class EnteredPhotoPath(val value: String): ModifyPlaceEvent()
    object SavePlace: ModifyPlaceEvent()
}
