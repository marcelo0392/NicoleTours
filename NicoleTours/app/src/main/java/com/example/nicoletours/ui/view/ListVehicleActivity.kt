package com.example.nicoletours.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.view.fragment.ListVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class ListVehicleActivity : AppCompatActivity() {

    private lateinit var binding:ActivityListVehicleBinding
    private val vehicleViewModel:VehicleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleViewModel.onCreate()

        getData()
    }

    private fun getData() {
        var lista:ArrayList<VehicleModel> = ArrayList()
        vehicleViewModel.getValue().observe(this, Observer {
            it.forEach{
                lista.add(it)
            }
            initRecycler(lista)
        })
    }

    private fun initRecycler(list: List<VehicleModel>) {
        binding.rvVehicle.layoutManager = LinearLayoutManager(this)
        binding.rvVehicle.adapter = RecyclerAdapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(vehicle:VehicleModel){

        val fragment = ListVehicleFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment1, fragment)
        fragmentTransaction.commit()

        vehicleViewModel.postVehicleSelect(vehicle)
//        Toast.makeText(this, vehicle.model, Toast.LENGTH_LONG).show()
    }
}