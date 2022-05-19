package com.example.nicoletours.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.nicoletours.R
import com.example.nicoletours.ui.view.fragment.ListVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class ListVehicleActivity : AppCompatActivity() {

    private val vehicleViewModel:VehicleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_vehicle)

        vehicleViewModel.onCreate()

        val fragment = ListVehicleFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment1, fragment)
        fragmentTransaction.commit()
    }
}