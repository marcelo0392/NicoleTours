package com.example.nicoletours.ui.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentNewVehicleBinding
import com.example.nicoletours.ui.viewModel.VehicleViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class NewVehicleFragment : Fragment() {

    private var _binding: FragmentNewVehicleBinding?=null
    private val binding get() = _binding!!
    private val vehicleViewModel: VehicleViewModel by activityViewModels()
    private var cImg = 0
    private var img1:String=""
    private var img2:String=""
    private var data1:Uri? = null
    private var data2:Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        departamento(view)
        inspection(view)
        category(view)

        binding.btnRegister.setOnClickListener {
            val vehicleNew=getData()
            vehicleViewModel.postNewVehicle(vehicleNew, data1!!, data2!!)
        }

        binding.imgVehicle1.setOnClickListener {
            requestPermission()
            cImg = 1
        }

        binding.imgVehicle2.setOnClickListener {
            requestPermission()
            cImg = 2
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
            if(cImg == 1) {
                data1 = result.data?.data!!
                binding.imgVehicle1.setImageURI(data1)
            }
            if(cImg == 2) {
                data2 = result.data?.data!!
                binding.imgVehicle2.setImageURI(data2)
            }
        }
    }

    private fun getData():VehicleModel {
        var ci = 0
        if(!TextUtils.isEmpty(binding.inputCi.text.toString())) ci = Integer.parseInt(binding.inputCi.text.toString())
        var age = 0
        if(!TextUtils.isEmpty(binding.inputAge.text.toString())) age = Integer.parseInt(binding.inputAge.text.toString())
        var capacity = 0
        if(!TextUtils.isEmpty(binding.inputCapacity.text.toString())) capacity = Integer.parseInt(binding.inputCapacity.text.toString())
        val dpto:String = binding.completeCiDpto.text.toString()

        val vehicle = VehicleModel(
            name = binding.inputName.text.toString(),
            ci = "$ci$dpto",
            category = binding.completeCategory.text.toString(),
            record = if(binding.cBoxRecord.isChecked) "SI" else "NO",
            inspection = binding.completeInspection.text.toString() +" Nº" + binding.inputInspectionNum.text.toString(),
            ruat = if(binding.cBoxRuat.isChecked) "SI" else "NO",
            soat = if(binding.cBoxSoat.isChecked) "SI" else "NO",
            age = age,
            brand = binding.inputBrand.text.toString(),
            capacity = capacity,
            model = binding.inputModel.text.toString(),
            plaque = binding.inputPlaque.text.toString(),
            airConditioning = if(binding.cBoxAir.isChecked) "SI" else "NO",
            heating = if(binding.cBoxHeating.isChecked) "SI" else "NO",
            chargers = if(binding.cBoxChargers.isChecked) "SI" else "NO",
            televisions = if(binding.cBoxTv.isChecked) "SI" else "NO",
            radio = if(binding.cBoxRadio.isChecked) "SI" else "NO",
            kit = if(binding.cBoxKit.isChecked) "SI" else "NO",
            extinguishers = if(binding.cBoxExtinguishers.isChecked) "SI" else "NO",
            recliners = if(binding.cBoxRecliners.isChecked) "SI" else "NO",
            seatBelt = if(binding.cBoxSeatBelt.isChecked) "SI" else "NO",
            image1 = img1,
            image2 = img2
        )
        return vehicle
    }

    private fun category(view: View) {
        val list = listOf<String>("A", "B", "C")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeCategory.threshold = 0
        binding.completeCategory.setAdapter(adapter)
    }

    private fun inspection(view: View) {
        val list = listOf<String>("SI", "NO")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeInspection.threshold = 0
        binding.completeInspection.setAdapter(adapter)
    }

    private fun departamento(view: View) {
        val list = listOf<String>("CH", "LP", "CB", "OR", "PT", "TJ", "SC", "BE", "PD")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeCiDpto.threshold = 0
        binding.completeCiDpto.setAdapter(adapter)
    }
}