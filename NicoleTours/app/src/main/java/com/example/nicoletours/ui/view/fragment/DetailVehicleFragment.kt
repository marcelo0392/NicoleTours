package com.example.nicoletours.ui.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentDetailVehicleBinding
import com.example.nicoletours.ui.viewModel.VehicleViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class DetailVehicleFragment() : Fragment() {

    private var _binding:FragmentDetailVehicleBinding?=null
    private val binding get() = _binding!!

    private val vehicleViewModel:VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailVehicleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        vehicleViewModel.getSelectedValue().observe(this, Observer {
            val listaString = ArrayList<String>()
            listaString.add(it.image1)
            listaString.add(it.image2)

            val list = mutableListOf<CarouselItem>()
            val carousel:ImageCarousel = binding.carousel
            list.add(CarouselItem(imageUrl = it.image1))
            list.add(CarouselItem(imageUrl = it.image2))

            carousel.setData(list)
            loadData(it)

            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    vehicleViewModel.postImageSelect(listaString)
                }
            }
        })

        binding.btnDetail.setOnClickListener {
            binding.layoutGeneral.isGone = false
            binding.btnDetail.isGone = true

//            requestPermission()
        }
    }


    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {

                context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                } == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }

                else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }else {
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->

        if (isGranted){
            pickPhotoFromGallery()
        }else{
            Toast.makeText(
                context,
                "Permission denied",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityResult.launch(intent)
    }

    private val startForActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data

            val folder: StorageReference =
                FirebaseStorage.getInstance().getReference().child("User")
            val fileName: StorageReference = folder.child("file" + data!!.lastPathSegment)

            fileName.putFile(data).addOnSuccessListener { taskSnapshot ->
                fileName.getDownloadUrl().addOnSuccessListener { uri ->
                    val img:String = uri.toString()
//                    val hashMap =
//                        HashMap<String, String>()
//                    hashMap["link"] = java.lang.String.valueOf(uri)
//                    myRef.setValue(hashMap)
                    Toast.makeText(context, img, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadData(it:VehicleModel) {
        binding.tvBrand.text = it.brand
        binding.tvModel.text = it.model
        binding.tvAge.text = it.age.toString()
        binding.tvPlaque.text = it.plaque
        binding.tvSeating.text = it.capacity.toString()
        binding.tvHeating.text = it.heating
        binding.tvCharger.text = it.chargers
        binding.tvTv.text = it.televisions
        binding.tvExtinguishers.text = it.extinguishers
        binding.tvKit.text = it.kit
        binding.tvRadio.text = it.radio
        binding.tvSeating.text = it.capacity.toString() + " Asientos"
        binding.tvAir.text = it.airConditioning

        binding.tvNom.text = it.name
        binding.tvCi.text = it.ci
        binding.tvRuat.text = it.ruat
        binding.tvSoat.text = it.soat
        binding.tvCategory.text = it.category
        binding.tvRecord.text = it.record
        binding.tvInspection.text = it.inspection

    }
}
