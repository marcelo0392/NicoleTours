package com.example.nicoletours.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.domain.GetLocationUseCase
import kotlinx.coroutines.launch

class LocationViewModel:ViewModel() {

    private val locationModel = MutableLiveData<List<LocationModel>>()
    private val locationSelected = MutableLiveData<String>()

    var getLocationUseCase = GetLocationUseCase()
    lateinit var result:List<LocationModel>

    fun onCreate() {
        viewModelScope.launch {
            result = getLocationUseCase()
            if(!result.isNullOrEmpty()){
                postLocation(result)
//                locationModel.postValue(result)
            }
        }
    }

    fun getValue():MutableLiveData<List<LocationModel>>{
        return locationModel
    }
    fun postLocation(result:List<LocationModel>){
        locationModel.postValue(result)
    }

    fun getLocationSelected():MutableLiveData<String>{
        return locationSelected
    }
    fun postLocationSelected(result:String){
        locationSelected.postValue(result)
    }
}