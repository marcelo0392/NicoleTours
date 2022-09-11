package com.example.nicoletours.data.model

import com.example.nicoletours.common.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VehicleModel(
                    @SerializedName("name") val name:String="",             //nombre completo
                    @SerializedName("lastName") val lastName:String="",
                    @SerializedName("mLastName") val mLastName:String="",
                    @SerializedName("ci") val ci:Int=0,                     //ci
                    @SerializedName("dpto") val dpto:String="",
                    @SerializedName("category") val category:String="",     //categoria de licencia
                    @SerializedName("record") val record:String="",         //antecedentes
                    @SerializedName("inspection") val inspection:String="", //inspeccion tecnica
                    @SerializedName("numInspection") val numInspection:Int=0,
                    @SerializedName("ruat") val ruat:String="",
                    @SerializedName("soat") val soat:String="",
                    @SerializedName("age") val age:Int=0,
                    @SerializedName("brand") val brand:String="",           //marca
                    @SerializedName("capacity") val capacity:Int=0,     //capacidad de asientos
                    @SerializedName("model") val model:String="",           //modelo
                    @SerializedName("plaque") val plaque:String="",         //Nº placa
                    @SerializedName("airConditioning") val airConditioning:String="",   //aire accondicionado
                    @SerializedName("heating") val heating:String="",       //calefaccion
                    @SerializedName("chargers") val chargers:String="",     //cargadores
                    @SerializedName("televisions") val televisions:String="",
                    @SerializedName("radio") val radio:String="",
                    @SerializedName("kit") val kit:String="",               //botiquin
                    @SerializedName("extinguishers") val extinguishers:String="",   //extintores
                    @SerializedName("recliners") val recliners:String="",   //asientos reclinables
                    @SerializedName("seatBelt") val seatBelt:String="",     //cinturones de seguridad
                    @SerializedName("image1") var image1:String="",
                    @SerializedName("image2") var image2:String=""
                    ) : Serializable

fun VehicleModel.toMap() = mapOf<String, Any>(
    DRIVER_NAME to name,
    DRIVER_LAST_NAME to lastName,
    DRIVER_M_LAST_NAME to mLastName,
    DRIVER_CI to ci,
    DRIVER_DPTO to dpto,
    DRIVER_CATEGORY to category,
    DRIVER_RECORD to record,
    VEHICLE_INSPECTION to inspection,
    VEHICLE_NUM_INSPECTION to numInspection,
    VEHICLE_RUAT to ruat,
    VEHICLE_SOAT to soat,
    VEHICLE_AGE to age,
    VEHICLE_BRAND to brand,
    VEHICLE_CAPACITY to capacity,
    VEHICLE_MODEL to model,
    VEHICLE_PLAQUE to plaque,
    VEHICLE_AIR to airConditioning,
    VEHICLE_HEATING to heating,
    VEHICLE_CHARGERS to chargers,
    VEHICLE_TV to televisions,
    VEHICLE_RADIO to radio,
    VEHICLE_KIT to kit,
    VEHICLE_EXTINGUISHERS to extinguishers,
    VEHICLE_RECLINERS to recliners,
    VEHICLE_SEAT_BELT to seatBelt,
    VEHICLE_IMAGE1 to image1,
    VEHICLE_IMAGE2 to image2
)
