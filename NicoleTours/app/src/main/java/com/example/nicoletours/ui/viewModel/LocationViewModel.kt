package com.example.nicoletours.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.provider.LocationProvider
import com.example.nicoletours.domain.GetLocationUseCase
import kotlinx.coroutines.launch

class LocationViewModel:ViewModel() {

    val locationModel = MutableLiveData<List<LocationModel>>()

    var getLocationUseCase = GetLocationUseCase()
    lateinit var result:List<LocationModel>

    fun onCreate() {
        viewModelScope.launch {
            result = getLocationUseCase()

            if(!result.isNullOrEmpty()){
                locationModel.postValue(result)
            }

        }
    }


    fun postLocation(){
        val res = listOf<LocationModel>(
            LocationModel("paris", "50000"),
            LocationModel("Francia", "60000")
        )
        locationModel.postValue(result)
    }
}