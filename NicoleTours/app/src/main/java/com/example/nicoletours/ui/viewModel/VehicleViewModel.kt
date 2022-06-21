package com.example.nicoletours.ui.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.domain.GetVehicleUseCase
import com.example.nicoletours.domain.postVehicleUseCase
import kotlinx.coroutines.launch

class VehicleViewModel:ViewModel() {

    private val vehicleModel = MutableLiveData<List<VehicleModel>>()
    private val vehicleSelect = MutableLiveData<VehicleModel>()
    private val imageSelect = MutableLiveData<ArrayList<String>>()

    var getVehicleUseCase = GetVehicleUseCase()

    lateinit var result:List<VehicleModel>
    lateinit var listImage:List<String>

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

    fun postImageSelect(result:ArrayList<String>){
        imageSelect.postValue(result)
    }
    fun getImageSelectedValue():MutableLiveData<ArrayList<String>>{
        return imageSelect
    }

    fun postNewVehicle(result: VehicleModel, img1:Uri, img2:Uri) {
        viewModelScope.launch {
            postVehicleUseCase().registerVehicle(result, img1, img2)
        }
    }

}