package com.example.nicoletours.data

import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.network.VehicleService

class VehicleRepository {
    private val api = VehicleService()

    suspend fun getListVehicle():List<VehicleModel>{
        val listResult = api.getAllVehicle()
        return listResult
    }
}