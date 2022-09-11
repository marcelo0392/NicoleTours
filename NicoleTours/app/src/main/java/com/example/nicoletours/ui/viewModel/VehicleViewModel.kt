package com.example.nicoletours.ui.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.domain.DeleteVehicleUseCase
import com.example.nicoletours.domain.GetVehicleUseCase
import com.example.nicoletours.domain.postVehicleUseCase
import kotlinx.coroutines.launch

class VehicleViewModel:ViewModel() {

    private val vehicleModel = MutableLiveData<List<VehicleModel>>()
    private val vehicleSelect = MutableLiveData<VehicleModel>()
    private val imageSelect = MutableLiveData<ArrayList<String>>()
    private val editVehicle = MutableLiveData<VehicleModel>()
    private val confirmRegister = MutableLiveData<Boolean>()

    var getVehicleUseCase = GetVehicleUseCase()

    fun getList() {
        viewModelScope.launch {
            val result = getVehicleUseCase()
            if(!result.isNullOrEmpty()){
                postVehicle(result)
            }
        }
    }

    fun getValue():MutableLiveData<List<VehicleModel>> = vehicleModel

    fun postVehicle(result:List<VehicleModel>) { vehicleModel.postValue(result) }

    fun postVehicleSelect(result:VehicleModel) { vehicleSelect.postValue(result) }

    fun getSelectedValue():MutableLiveData<VehicleModel> = vehicleSelect

    fun postImageSelect(result:ArrayList<String>) { imageSelect.postValue(result) }

    fun getImageSelectedValue():MutableLiveData<ArrayList<String>> = imageSelect

    fun postNewVehicle(result: VehicleModel, img1:Uri, img2:Uri) {
        confirmRegister.postValue(true)
        viewModelScope.launch {
            val res:Boolean = postVehicleUseCase().registerVehicle(result, img1, img2)
            confirmRegister.postValue(false)
        }
    }

    fun getConfirmRegister():MutableLiveData<Boolean> = confirmRegister

    fun postVehicleEdit(result:VehicleModel) { editVehicle.postValue(result) }

    fun getVehicleEdit():MutableLiveData<VehicleModel> = editVehicle

    fun deleteVehicle(result:String) {
        viewModelScope.launch {
            DeleteVehicleUseCase().deleteVehicle(result)
        }
    }
}