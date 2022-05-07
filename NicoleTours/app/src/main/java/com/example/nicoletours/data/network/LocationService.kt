package com.example.nicoletours.data.network

import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.nicoletours.data.model.LocationModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Response

class LocationService:LocationRepo {

//    private val api:LocationRepo = TODO()
//
//    suspend fun getLocation():List<LocationModel>{
//        return withContext(Dispatchers.IO){
//            val response =api.getAllLocations()
//                response.body() ?: emptyList()
//        }
//    }

    override suspend fun getAllLocations():List<LocationModel> {
        return withContext(Dispatchers.IO) {
            val list = ArrayList<LocationModel>()
            val db = Firebase.firestore

            db.collection("destinos").get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val location: String? = document.getString("location")
                        val cost: String? = document.getString("cost")
                        val locationModel = LocationModel(location!!, cost!!)
                        list.add(locationModel)
                    }

//                val dato = it.toObject<LocationModel>()
//                if (dato != null) {
//                    list.add(dato)
//                }
                }
            list
//            return list
        }
    }


}