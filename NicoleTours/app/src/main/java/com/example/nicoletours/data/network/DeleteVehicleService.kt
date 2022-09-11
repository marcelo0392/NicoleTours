package com.example.nicoletours.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class DeleteVehicleService {

    private val db = FirebaseFirestore.getInstance()

    suspend fun delete(result: String){

        db.collection("Vehicle").document(result).delete().await()

        val folder: StorageReference = FirebaseStorage.getInstance().getReference().child("Vehicle/$result")
        folder.child(result + "1").delete().addOnSuccessListener {  }
        folder.child(result + "2").delete().addOnSuccessListener {  }
    }
}