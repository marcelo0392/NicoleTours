package com.example.nicoletours.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.view.fragment.DetailVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

open class ListVehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListVehicleBinding
    private val vehicleViewModel: VehicleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleViewModel.onCreate()
        getData()
    }

    private fun getData() {
        val lista: ArrayList<VehicleModel> = ArrayList()
        vehicleViewModel.getValue().observe(this, Observer {
            it.forEach {
                lista.add(it)
            }
            initRecycler(lista)
        })
    }

    private fun initRecycler(list: List<VehicleModel>) {
        binding.rvVehicle.layoutManager = LinearLayoutManager(this)
        binding.rvVehicle.adapter = RecyclerAdapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(vehicle: VehicleModel) {
        val i = Intent(this, VehicleDetailActivity()::class.java)
        val bundle = Bundle()
        bundle.putSerializable("vehicle", vehicle)
        i.putExtras(bundle)
        startActivity(i)

        vehicleViewModel.postVehicleSelect(vehicle)
    }
}