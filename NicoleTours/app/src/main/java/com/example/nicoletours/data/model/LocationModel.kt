package com.example.nicoletours.data.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("location") val location:String="",
    @SerializedName("cost") val cost:String=""
)
