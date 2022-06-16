package com.example.nicoletours.domain

import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.network.PostVehicleService

class postVehicleUseCase {

    suspend fun postVehicle(result:VehicleModel){
        PostVehicleService().createVehicle(result)
    }

}