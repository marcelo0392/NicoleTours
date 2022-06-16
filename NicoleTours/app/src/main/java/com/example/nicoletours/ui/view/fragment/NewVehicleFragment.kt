package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentNewVehicleBinding
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class NewVehicleFragment : Fragment() {

    private var _binding: FragmentNewVehicleBinding?=null
    private val binding get() = _binding!!
    private val vehicleViewModel: VehicleViewModel by activityViewModels()

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
            vehicleViewModel.postNewVehicle(vehicleNew)
//            createVehicle(vehicleNew)
        }
    }

    private fun getData():VehicleModel {
        val name:String = binding.inputName.text.toString()
        val inspectionNum:String = binding.inputInspectionNum.text.toString()
        val brand:String = binding.inputBrand.text.toString()
        val model:String = binding.inputModel.text.toString()
        val plaque:String = binding.inputPlaque.text.toString()
        var ci = 0
        if(!TextUtils.isEmpty(binding.inputCi.text.toString())) ci = Integer.parseInt(binding.inputCi.text.toString())
        var age = 0
        if(!TextUtils.isEmpty(binding.inputAge.text.toString())) age = Integer.parseInt(binding.inputAge.text.toString())
        var capacity = 0
        if(!TextUtils.isEmpty(binding.inputCapacity.text.toString())) capacity = Integer.parseInt(binding.inputCapacity.text.toString())

        //complete
        val dpto:String = binding.completeCiDpto.text.toString()
        val category:String = binding.completeCategory.text.toString()
        val inspection:String = binding.completeInspection.text.toString()

        //check
        val record = if(binding.cBoxRecord.isChecked) "SI" else "NO"
        val ruat = if(binding.cBoxRuat.isChecked) "SI" else "NO"
        val soat = if(binding.cBoxSoat.isChecked) "SI" else "NO"
        val air = if(binding.cBoxAir.isChecked) "SI" else "NO"
        val heating = if(binding.cBoxHeating.isChecked) "SI" else "NO"
        val chargers = if(binding.cBoxChargers.isChecked) "SI" else "NO"
        val tv = if(binding.cBoxTv.isChecked) "SI" else "NO"
        val radio = if(binding.cBoxRadio.isChecked) "SI" else "NO"
        val kit = if(binding.cBoxKit.isChecked) "SI" else "NO"
        val extinguishers = if(binding.cBoxExtinguishers.isChecked) "SI" else "NO"
        val recliners = if(binding.cBoxRecliners.isChecked) "SI" else "NO"
        val seatBelt = if(binding.cBoxSeatBelt.isChecked) "SI" else "NO"

        Toast.makeText(context, "asd $category", Toast.LENGTH_LONG).show()

        val vehicleNew = VehicleModel(
            name = name,
            ci = "$ci $dpto",
            category = category,
            record = record,
            inspection = "$inspection Nº$inspectionNum",
            ruat = ruat,
            soat = soat,
            age = age,
            brand = brand,
            capacity = capacity,
            model = model,
            plaque = plaque,
            airConditioning = air,
            heating = heating,
            chargers = chargers,
            televisions = tv,
            radio = radio,
            kit = kit,
            extinguishers = extinguishers,
            recliners = recliners,
            seatBelt = seatBelt
        )
        return vehicleNew
    }

//    private fun createVehicle(vehicle: VehicleModel) {
//        Firebase.firestore
//            .collection("Vehicle")
//            .add(vehicle.toMap())
//    }

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