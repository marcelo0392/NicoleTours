package com.example.nicoletours.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.R
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityListLocationsBinding
import com.example.nicoletours.databinding.ActivityListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.RecyclerAdapterLocation
import com.example.nicoletours.ui.viewModel.LocationViewModel
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class Activity_List_Locations : AppCompatActivity() {

    private lateinit var binding: ActivityListLocationsBinding
    private val locationViewModel: LocationViewModel by viewModels()
    //var fragmentNewVehicle: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationViewModel.onCreate()

        locationViewModel.getValue().observe(this, Observer {
            val lista: ArrayList<LocationModel> = ArrayList()
            it.forEach {
                lista.add(it)
            }
            initRecycler(lista)
        })
    }

    private fun initRecycler(list: List<LocationModel>) {
        binding.rvLocation.layoutManager = LinearLayoutManager(this)
        binding.rvLocation.adapter = RecyclerAdapterLocation(list) { onItemSelected(it) }
    }

    private fun onItemSelected(location: LocationModel) {
        val i = Intent(this, LocationDetailActivity()::class.java)
        val bundle = Bundle()
        bundle.putSerializable("location", location)
        i.putExtras(bundle)
        startActivity(i)

        locationViewModel.postLocationItemSelected(location)
    }
}