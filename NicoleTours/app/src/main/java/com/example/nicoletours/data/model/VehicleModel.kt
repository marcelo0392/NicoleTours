package com.example.nicoletours.data.model

import com.google.gson.annotations.SerializedName

data class VehicleModel(
                    @SerializedName("plaque") val plaque:String="",
                    @SerializedName("model") val model:String="",
                    @SerializedName("age") val age:Int=0,
                    @SerializedName("seating") val seating:Int=0,
                    @SerializedName("type") val type:String="",
                    @SerializedName("image") val image:String=""
                    )
