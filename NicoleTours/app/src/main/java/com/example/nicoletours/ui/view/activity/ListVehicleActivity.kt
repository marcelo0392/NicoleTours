package com.example.nicoletours.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.view.fragment.NewVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

open class ListVehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListVehicleBinding
    private val vehicleViewModel: VehicleViewModel by viewModels()
    var fragmentNewVehicle: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleViewModel.getList()

        vehicleViewModel.getValue().observe(this, Observer {
            val lista: ArrayList<VehicleModel> = ArrayList()
            it.forEach {
                lista.add(it)
            }
            initRecycler(lista)
        })

        binding.btnAdd.setOnClickListener {
            binding.appbar.isGone = true
            binding.scroll.isGone = true
            binding.btnAdd.isGone = true
            fragmentNewVehicle = NewVehicleFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment1, NewVehicleFragment())
            fragmentTransaction.commit()
        }

        binding.refresh.setOnRefreshListener {
            vehicleViewModel.getList()
            binding.refresh.isRefreshing = false
            Toast.makeText(this, "Lista actualizada", Toast.LENGTH_LONG).show()
        }
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

    private fun removeFragment(fragment:Fragment) {
        val fragmentTransactions = supportFragmentManager.beginTransaction()
        fragmentTransactions.remove(fragment)
        fragmentTransactions.commit()
    }

    override fun onBackPressed() {
        if(fragmentNewVehicle is NewVehicleFragment) {
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragment1)!!
            removeFragment(fragmento)
//            fragmentNewVehicle = null
            binding.appbar.isGone = false
            binding.scroll.isGone = false
            binding.btnAdd.isGone = false
        }else {
            super.onBackPressed()
        }
    }

}