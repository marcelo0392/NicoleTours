package com.example.nicoletours.domain

import com.example.nicoletours.data.LocationRepository
import com.example.nicoletours.data.model.LocationModel

class GetLocationUseCase {

    private val repositori = LocationRepository()

    suspend operator fun invoke():List<LocationModel>{
        return repositori.getListLocation()
    }
}