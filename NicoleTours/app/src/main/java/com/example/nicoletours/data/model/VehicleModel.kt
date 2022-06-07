package com.example.nicoletours.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VehicleModel(
                    @SerializedName("name") val name:String="",             //nombre completo
                    @SerializedName("ci") val ci:String="",                 //ci
                    @SerializedName("category") val category:String="",     //categoria de licencia
                    @SerializedName("record") val record:String="",         //antecedentes
                    @SerializedName("inspection") val inspection:String="", //inspeccion tecnica
                    @SerializedName("ruat") val ruat:String="",
                    @SerializedName("soat") val soat:String="",
                    @SerializedName("age") val age:Int=0,
                    @SerializedName("brand") val brand:String="",           //marca
                    @SerializedName("capacity") val capacity:String="",     //capacidad de asientos
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
                    @SerializedName("image1") val image1:String="",
                    @SerializedName("image2") val image2:String=""
                    ) : Serializable
