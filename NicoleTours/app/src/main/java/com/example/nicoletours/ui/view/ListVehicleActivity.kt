package com.example.nicoletours.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.data.model.toMap
import com.example.nicoletours.databinding.ActivityListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.view.fragment.DetailVehicleFragment
import com.example.nicoletours.ui.view.fragment.ImagesFragment
import com.example.nicoletours.ui.view.fragment.NewVehicleFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class ListVehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListVehicleBinding
    private val vehicleViewModel: VehicleViewModel by viewModels()
    var fragmentNewVehicle: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleViewModel.onCreate()
        getData()

        binding.btnAdd.setOnClickListener {

            binding.appbar.isGone = true
            binding.scroll.isGone = true
            binding.btnAdd.isGone = true

            fragmentNewVehicle = NewVehicleFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment1, fragmentNewVehicle as NewVehicleFragment)
            fragmentTransaction.commit()


//            Toast.makeText(this, "asdlkjalksdj", Toast.LENGTH_LONG).show()
//            val vehicleNew = VehicleModel(
//                name = "Lucho",
//                ci = "12312312 CBBA",
//                category = "A",
//                record = "SI",
//                inspection = "NO",
//                ruat = "NO",
//                soat = "NO",
//                age = 1980,
//                brand = "patito",
//                capacity = "123 pasajeros",
//                model = "1950",
//                plaque = "asd123",
//                airConditioning = "NO",
//                heating = "NO",
//                chargers = "NO",
//                televisions = "NO",
//                radio = "NO",
//                kit = "NO",
//                extinguishers = "NO",
//                recliners = "NO",
//                seatBelt = "NO"
//            )
//            createVehicle(vehicleNew)
        }
    }

    private fun createVehicle(vehicle: VehicleModel) {
        Firebase.firestore
            .collection("Vehicle")
            .add(vehicle.toMap())
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

    private fun removeFragment(fragment:Fragment) {
        val fragmentTransactions = supportFragmentManager.beginTransaction()
        fragmentTransactions.remove(fragment)
        fragmentTransactions.commit()
    }

    override fun onBackPressed() {
        if(fragmentNewVehicle is NewVehicleFragment) {
            Toast.makeText(this, "SALIENDO", Toast.LENGTH_LONG).show()
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragment1)!!
            removeFragment(fragmento)
            fragmentNewVehicle = null
            binding.appbar.isGone = false
            binding.scroll.isGone = false
            binding.btnAdd.isGone = false
        }else {
            super.onBackPressed()
        }
    }

}