package com.example.nicoletours.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityVehicleDetailBinding
import com.example.nicoletours.ui.view.fragment.DetailVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class VehicleDetailActivity() : AppCompatActivity() {


    private val vehicleViewModel: VehicleViewModel by viewModels()
    private lateinit var binding:ActivityVehicleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var bundle: Bundle? = intent.extras
//        val vehicle:VehicleModel = bundle!!.getSerializable("vehicle") as VehicleModel
        val vehicle = intent.getSerializableExtra("vehicle") as VehicleModel

        Toast.makeText(this, vehicle.type, Toast.LENGTH_LONG).show()

        val fragment = DetailVehicleFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentDetail, fragment)
        fragmentTransaction.commit()

        vehicleViewModel.postVehicleSelect(vehicle)
    }
}