package com.example.nicoletours.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.provider.LocationProvider

class LocationViewModel:ViewModel() {

    val locationModel = MutableLiveData<List<LocationModel>>()

    fun postLocation(){
        locationModel.postValue(LocationProvider.getLocation())
    }
}