package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.LocationModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LocationService:LocationRepo {

    private val db = Firebase.firestore

    override suspend fun getAllLocations():List<LocationModel> {
        return  withContext(Dispatchers.IO) {
            val list = ArrayList<LocationModel>()
//            async {
                val docref = db.collection("destinos")
                    docref.addSnapshotListener{snapshot, e ->
                    if(e != null){
                        return@addSnapshotListener
                    }
                    if(snapshot != null){
                        for(doc in snapshot!!) {
                            list.add(doc.toObject<LocationModel>())
                    }
                    }
                }
//            }.await()

//            val db = Firebase.firestore
//
//            db.collection("destinos").get()
//                .addOnSuccessListener { result ->
//                    for (document in result) {
//                       val loc = document.toObject<LocationModel>()
////////                        val location: String? = document.getString("location")
////////                        val cost: String? = document.getString("cost")
////////                        val locationModel = LocationModel(location!!, cost!!)
//                        list.add(loc)
//                    }
//
////////                val dato = it.toObject<LocationModel>()
////////                if (dato != null) {
////////                    list.add(dato)
////////                }
//               }.await()
            delay(1000)
            list
        }
    }
}