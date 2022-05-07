package com.example.nicoletours.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardCotizar.setOnClickListener {
            val i = Intent(this, QuoteActivity::class.java)
            startActivity(i)
        }

//        var nom:String=""
//        db.collection("destinos").whereEqualTo("nombre", "Olivia").get().addOnSuccessListener {
//            for(document in it){
//                nom += document.get("location").toString()
//            }
//            binding.tvMensaje.text = nom

        val list=ArrayList<LocationModel>()

        db.collection("destinos").get()
            .addOnSuccessListener {result->
                for(document in result){
                    val location:String?=document.getString("location")
                    val cost:String?=document.getString("cost")
                    val locationModel = LocationModel(location!!, cost!!)
                    list.add(locationModel)
                }
                binding.tvMensaje.text = list[0].location
        }


    }
}