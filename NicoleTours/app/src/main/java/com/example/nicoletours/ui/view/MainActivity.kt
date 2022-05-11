package com.example.nicoletours.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
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


        var m = ""
//        val docRef = db.collection("destinos")
//        docRef.get().addOnCompleteListener {task->
//            val document = task.result
//            for(doc in document){
//                if (document != null) {
//                    m += "\n" + doc.getString("location").toString()
//                }
//            }
//            binding.tvMensaje.text = m
//        }



//        db.collection("destinos").addSnapshotListener{ snapshot, firebaseError->
//
//            for(doc in snapshot!!){
//                m += " " + doc.getString("location").toString()
//            }
//            binding.tvMensaje.text = m
//            m = ""
//        }



//        val firebaseDatabase = FirebaseDatabase.getInstance()
//        val reference = firebaseDatabase.getReference()
//        reference.child("destinos").addListenerForSingleValueEvent(object :ValueEventListener{
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val children = snapshot.children
//                children.forEach{
//                    m += it.child("location").toString()
//                }
//                binding.tvMensaje.text = m
//            }
//        })


//        val list=ArrayList<LocationModel>()
//
//        db.collection("destinos").get()
//            .addOnSuccessListener {result->
//                for(document in result){
//                    val location:String?=document.getString("location")
//                    val cost:String?=document.getString("cost")
//                    val locationModel = LocationModel(location!!, cost!!)
//                    list.add(locationModel)
//                }
//                binding.tvMensaje.text = list[0].location
//        }


    }
}