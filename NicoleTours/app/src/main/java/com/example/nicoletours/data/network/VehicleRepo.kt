package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.VehicleModel

interface VehicleRepo {
    suspend fun getAllVehicle():List<VehicleModel>
}