package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.LocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationService {

    private val api:LocationRepo = TODO()

    suspend fun getLocation():List<LocationModel>{
        return withContext(Dispatchers.IO){
            val response =api.getAllLocations()
                response.body() ?: emptyList()
        }
    }


}