package com.example.nicoletours.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.domain.GetLocationUseCase
import com.example.nicoletours.domain.GetVehicleUseCase
import kotlinx.coroutines.launch

class VehicleViewModel:ViewModel() {

    private val vehicleModel = MutableLiveData<List<VehicleModel>>()
    private val vehicleSelect = MutableLiveData<VehicleModel>()

    var getVehicleUseCase = GetVehicleUseCase()
    lateinit var result:List<VehicleModel>

    fun onCreate() {
        viewModelScope.launch {
            result = getVehicleUseCase()
            if(!result.isNullOrEmpty()){
                postVehicle(result)
//                locationModel.postValue(result)
            }
        }
    }

    fun getValue():MutableLiveData<List<VehicleModel>>{
        return vehicleModel
    }
    fun postVehicle(result:List<VehicleModel>){
        vehicleModel.postValue(result)
    }

    fun postVehicleSelect(result:VehicleModel){
        vehicleSelect.postValue(result)
    }
    fun getSelectedValue():MutableLiveData<VehicleModel>{
        return vehicleSelect
    }
}