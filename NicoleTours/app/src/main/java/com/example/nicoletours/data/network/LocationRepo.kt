package com.example.nicoletours.data.network

import com.example.nicoletours.data.model.LocationModel
import retrofit2.Response

interface LocationRepo {

    suspend fun getAllLocations():Response<List<LocationModel>>
}