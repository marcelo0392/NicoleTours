package com.example.nicoletours.domain

import com.example.nicoletours.data.VehicleRepository
import com.example.nicoletours.data.model.VehicleModel

class GetVehicleUseCase {
    private val repositori = VehicleRepository()

    suspend operator fun invoke():List<VehicleModel>{
        return repositori.getListVehicle()
    }
}