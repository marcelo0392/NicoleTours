package com.example.nicoletours.domain

import com.example.nicoletours.data.network.DeleteVehicleService

class DeleteVehicleUseCase {

    suspend fun deleteVehicle(result:String){
        DeleteVehicleService().delete(result)
    }
}