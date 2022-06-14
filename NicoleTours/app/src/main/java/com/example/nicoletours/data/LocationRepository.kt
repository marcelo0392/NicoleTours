package com.example.nicoletours.data

import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.network.LocationService

class LocationRepository {

    private val api = LocationService()

    suspend fun getListLocation():List<LocationModel>{
        val listResult = api.getAllLocations()
        return listResult
    }
}