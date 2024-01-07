package com.example.helloworld.visuals.Edit

import androidx.lifecycle.ViewModel
import com.example.helloworld.data.PlaceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val placeRepo: PlaceRepo) : ViewModel() {


    }