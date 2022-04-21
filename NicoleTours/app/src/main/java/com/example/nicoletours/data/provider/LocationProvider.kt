package com.example.nicoletours.data.provider

import com.example.nicoletours.data.model.LocationModel

class LocationProvider {

    companion object {
        fun getLocation():List<LocationModel>{
            location.add(LocationModel(location = "Vacas", cost = "70"))
            return location
        }

        private val location = mutableListOf<LocationModel>(
            LocationModel(
                location = "Iriuni",
                cost = "50"
            ),
            LocationModel(
                location = "Pairumani",
                cost ="80"
            ),
            LocationModel(location = "Villa Tunari", cost="150")
        )
    }
}