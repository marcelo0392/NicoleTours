package com.example.nicoletours.domain

import android.net.Uri
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.network.PostVehicleService

class postVehicleUseCase() {

    suspend fun registerVehicle(result:VehicleModel, img1: Uri, img2: Uri):Boolean{
        return PostVehicleService().createVehicle(result, img1, img2)
    }
}