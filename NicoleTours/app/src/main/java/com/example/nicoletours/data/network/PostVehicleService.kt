package com.example.nicoletours.data.network

import android.net.Uri
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.model.toMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class PostVehicleService{
    suspend fun createVehicle(vehicle: VehicleModel, img1: Uri, img2: Uri) {

        val folder: StorageReference = FirebaseStorage.getInstance().getReference().child("Vehicle")

        val fileName1: StorageReference = folder.child(vehicle.plaque).child(vehicle.plaque + "1")
        fileName1.putFile(img1).addOnSuccessListener { taskSnapshot ->
            fileName1.getDownloadUrl().addOnSuccessListener { uri ->
                vehicle.image1 = uri.toString()
            }
        }.await()

        val fileName2: StorageReference = folder.child(vehicle.plaque).child(vehicle.plaque + "2")
        fileName2.putFile(img2).addOnSuccessListener { taskSnapshot ->
            fileName2.getDownloadUrl().addOnSuccessListener { uri ->
                vehicle.image2 = uri.toString()

                Firebase.firestore.collection("Vehicle").document(vehicle.plaque)
                    .set(vehicle.toMap())
            }
        }.await()
    }
}