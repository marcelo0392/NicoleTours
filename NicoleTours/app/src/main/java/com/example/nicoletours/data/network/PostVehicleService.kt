package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.model.toMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PostVehicleService{
    suspend fun createVehicle(vehicle: VehicleModel) {
        Firebase.firestore
            .collection("Vehicle")
            .add(vehicle.toMap())
            .await()
    }
}