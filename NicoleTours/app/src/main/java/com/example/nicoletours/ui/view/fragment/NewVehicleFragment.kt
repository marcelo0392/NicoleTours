package com.example.nicoletours.ui.view.fragment

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentNewVehicleBinding
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class NewVehicleFragment : Fragment(), AdapterView.OnItemClickListener {

    private var _binding: FragmentNewVehicleBinding?=null
    private val binding get() = _binding!!
    private val vehicleViewModel: VehicleViewModel by activityViewModels()
    private var cImg = 0
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
            if(validator()) {
                if(validatorImage()) {
                    vehicleViewModel.postNewVehicle(getData(), data1!!, data2!!)
                }else{
                    Toast.makeText(context, "Pulse sobre las imagenes para cargar desde galeria", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context, "Porfavor ingrese los campos marcados con rojo", Toast.LENGTH_LONG).show()
            }
            vehicleViewModel.getConfirmRegister().observe(this, Observer {
                binding.progress.isVisible = it
                if(it){
                    Toast.makeText(context, "Porfavor espere", Toast.LENGTH_LONG).show()
                }else{
                    var builder = AlertDialog.Builder(context!!)
                    builder.setMessage("Registrado Correctamente")
                    builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { d, i -> })
                    var dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            })
        }

        binding.imgVehicle1.setOnClickListener {
            requestPermission()
            cImg = 1
        }
        binding.imgVehicle2.setOnClickListener {
            requestPermission()
            cImg = 2
        }

        vehicleViewModel.getVehicleEdit().observe(this, Observer {
            val vehicle = it
            Toast.makeText(context, "Prueba OBSERVER", Toast.LENGTH_LONG).show()
            binding.collapsingToolbar.title = "EDITAR"
            loadData(vehicle, view)
        })
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

        with(binding.completeInspection){
            onItemClickListener = this@NewVehicleFragment
        }
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

        val item = parent?.getItemAtPosition(position).toString()
        binding.edInspectionNumber.isEnabled = if(item == "SI") true else false
    }

    private fun departamento(view: View) {
        val list = listOf<String>("CH", "LP", "CB", "OR", "PT", "TJ", "SC", "BE", "PD")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeCiDpto.threshold = 0
        binding.completeCiDpto.setAdapter(adapter)
    }

    private fun loadData(it:VehicleModel, view: View) {
        binding.inputName.setText(it.name as String?)
        binding.inputLastName.setText(it.lastName as String?)
        binding.inputMLastName.setText(it.mLastName as String?)
        binding.inputBrand.setText(it.brand as String?)
        binding.inputModel.setText(it.model as String?)
        binding.inputPlaque.setText(it.plaque as String?)

        binding.completeCiDpto.setText(it.dpto as String?)
        binding.completeCategory.setText(it.category as String?)
        binding.completeInspection.setText(it.inspection as String?)

        binding.inputCi.setText(it.ci.toString(), TextView.BufferType.EDITABLE)
        binding.inputAge.setText(it.age.toString(), TextView.BufferType.EDITABLE)
        binding.inputCapacity.setText(it.capacity.toString(), TextView.BufferType.EDITABLE)
        binding.inputInspectionNum.setText(it.numInspection.toString(), TextView.BufferType.EDITABLE)

        if(it.record == "SI") binding.cBoxRecord.isChecked = true
        if(it.ruat == "SI") binding.cBoxRuat.isChecked = true
        if(it.soat == "SI") binding.cBoxSoat.isChecked = true
        if(it.airConditioning == "SI") binding.cBoxAir.isChecked = true
        if(it.heating == "SI") binding.cBoxHeating.isChecked = true
        if(it.chargers == "SI") binding.cBoxChargers.isChecked = true
        if(it.televisions == "SI") binding.cBoxTv.isChecked = true
        if(it.radio == "SI") binding.cBoxRadio.isChecked = true
        if(it.kit == "SI") binding.cBoxKit.isChecked = true
        if(it.extinguishers == "SI") binding.cBoxExtinguishers.isChecked = true
        if(it.recliners == "SI") binding.cBoxRecliners.isChecked = true
        if(it.seatBelt == "SI") binding.cBoxSeatBelt.isChecked = true

        departamento(view)
        inspection(view)
        category(view)

        Glide.with(binding.imgVehicle1.context).load(it.image1).into(binding.imgVehicle1)
        Glide.with(binding.imgVehicle2.context).load(it.image2).into(binding.imgVehicle2)
    }

    private fun getData():VehicleModel {
        var ci = 0
        if(!TextUtils.isEmpty(binding.inputCi.text.toString())) ci = Integer.parseInt(binding.inputCi.text.toString())
        var age = 0
        if(!TextUtils.isEmpty(binding.inputAge.text.toString())) age = Integer.parseInt(binding.inputAge.text.toString())
        var capacity = 0
        if(!TextUtils.isEmpty(binding.inputCapacity.text.toString())) capacity = Integer.parseInt(binding.inputCapacity.text.toString())
        var numInsp = 0
        if(!TextUtils.isEmpty(binding.inputInspectionNum.text.toString())) numInsp = Integer.parseInt(binding.inputInspectionNum.text.toString())

        val vehicle = VehicleModel(
            name = binding.inputName.text.toString(),
            lastName = binding.inputLastName.text.toString(),
            mLastName = binding.inputMLastName.text.toString(),
            brand = binding.inputBrand.text.toString(),
            model = binding.inputModel.text.toString(),
            plaque = binding.inputPlaque.text.toString(),

            dpto = binding.completeCiDpto.text.toString(),
            category = binding.completeCategory.text.toString(),
            inspection = binding.completeInspection.text.toString(),

            numInspection = numInsp,
            age = age,
            ci = ci,
            capacity = capacity,

            record = if(binding.cBoxRecord.isChecked) "SI" else "NO",
            ruat = if(binding.cBoxRuat.isChecked) "SI" else "NO",
            soat = if(binding.cBoxSoat.isChecked) "SI" else "NO",
            airConditioning = if(binding.cBoxAir.isChecked) "SI" else "NO",
            heating = if(binding.cBoxHeating.isChecked) "SI" else "NO",
            chargers = if(binding.cBoxChargers.isChecked) "SI" else "NO",
            televisions = if(binding.cBoxTv.isChecked) "SI" else "NO",
            radio = if(binding.cBoxRadio.isChecked) "SI" else "NO",
            kit = if(binding.cBoxKit.isChecked) "SI" else "NO",
            extinguishers = if(binding.cBoxExtinguishers.isChecked) "SI" else "NO",
            recliners = if(binding.cBoxRecliners.isChecked) "SI" else "NO",
            seatBelt = if(binding.cBoxSeatBelt.isChecked) "SI" else "NO",
            image1 = "",
            image2 = ""
        )
        return vehicle
    }

    private fun validator():Boolean {
        var res:Boolean = true
        if(binding.inputName.text.toString()=="") {
            binding.edName.error = "Ingrese un nombre valido"
            res = false
        } else binding.edName.error = null

        if(binding.inputLastName.text.toString()=="") {
            binding.edLastName.error = "Ingrese un apellido valido"
            res = false
        } else binding.edLastName.error = null

        if(binding.inputMLastName.text.toString()=="") {
            binding.edMLastName.error = "Ingrese un apellido valido"
            res = false
        } else binding.edMLastName.error = null

        if(binding.inputCi.text.toString()=="") {
            binding.edCi.error = "Ingrese el Número de Ci"
            res = false
        } else binding.edCi.error = null

        if(binding.inputInspectionNum.text.toString()=="" && binding.completeInspection.text.toString()=="SI") {
            binding.edInspectionNumber.error = "Ingrese el Numero de Inspeccion"
            res = false
        } else binding.edInspectionNumber.error = null

        if(binding.inputAge.text.toString()=="") {
            binding.edAge.error = "Ingrese el modelo de chasis"
            res = false
        } else binding.edAge.error = null

        if(binding.inputBrand.text.toString()=="") {
            binding.edBrand.error = "Ingrese la marca del Vehiculo"
            res = false
        } else binding.edBrand.error = null

        if(binding.inputCapacity.text.toString()=="") {
            binding.edCapacity.error = "Indique la capacidad de pasajeros"
            res = false
        } else binding.edCapacity.error = null

        if(binding.inputModel.text.toString()=="") {
            binding.edModel.error = "Ingrese el modelo del Vehiculo"
            res = false
        } else binding.edModel.error = null

        if(binding.inputPlaque.text.toString()=="") {
            binding.edPlaque.error = "Ingrese el Numero de placa"
            res = false
        } else binding.edPlaque.error = null

        if(binding.completeInspection.text.toString()=="") {
            binding.edInspection.error = "Seleccione una opción"
            res = false
        } else binding.edInspection.error = null

        if(binding.completeCategory.text.toString()=="") {
            binding.edCategory.error = "Seleccione una opción"
            res = false
        } else binding.edCategory.error = null

        if(binding.completeCiDpto.text.toString()=="") {
            binding.edCiDpto.error = "Seleccione una opción"
            res = false
        } else binding.edCiDpto.error = null
        return res
    }

    private fun validatorImage(): Boolean {
        var res = true
        if(data1 == null || data2 == null){
            Toast.makeText(context, "Seleccione las imagenes", Toast.LENGTH_LONG).show()
            res = false
        }
        return res
    }
}