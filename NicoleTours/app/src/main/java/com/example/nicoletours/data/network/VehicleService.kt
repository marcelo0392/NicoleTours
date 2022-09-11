package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.VehicleModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class VehicleService:VehicleRepo {

    private val db = Firebase.firestore

    override suspend fun getAllVehicle(): List<VehicleModel> {
        return  withContext(Dispatchers.IO) {
            var list = ArrayList<VehicleModel>()
            val docref = db.collection("Vehicle")
            docref.addSnapshotListener{snapshot, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    for(doc in snapshot!!) {
                        list.add(doc.toObject<VehicleModel>())
                    }
                }
            }
            delay(1000)
            list
        }
    }
}