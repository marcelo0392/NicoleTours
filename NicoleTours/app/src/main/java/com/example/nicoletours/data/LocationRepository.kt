package com.example.nicoletours.data

import android.provider.CallLog
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.network.LocationService
import com.example.nicoletours.data.provider.LocationProvider

class LocationRepository {

    private val api = LocationService()

    suspend fun getListLocation():List<LocationModel>{
        val listResult = api.getAllLocations()
//        LocationProvider.location = listResult
        return listResult
    }
}