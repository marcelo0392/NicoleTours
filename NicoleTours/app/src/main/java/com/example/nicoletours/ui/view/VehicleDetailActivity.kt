package com.example.nicoletours.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityVehicleDetailBinding
import com.example.nicoletours.ui.view.fragment.DetailVehicleFragment
import com.example.nicoletours.ui.view.fragment.ImagesFragment
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class VehicleDetailActivity() : AppCompatActivity() {

    private val vehicleViewModel: VehicleViewModel by viewModels()
    private lateinit var binding:ActivityVehicleDetailBinding
    var fragment:Fragment = DetailVehicleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vehicle = intent.getSerializableExtra("vehicle") as VehicleModel

//        Toast.makeText(this, vehicle.type, Toast.LENGTH_LONG).show()
        callFragmentDetall(fragment)
        vehicleViewModel.postVehicleSelect(vehicle)

        vehicleViewModel.getImageSelectedValue().observe(this, Observer {
//            Toast.makeText(this, vehicle.image, Toast.LENGTH_LONG).show()
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetail)!!
            if(fragmento != null) {
                removeFragment(fragmento)
                fragment = ImagesFragment()
                callFragmentImage(fragment, it)
            }
        })
    }

    private fun callFragmentImage(fragment: Fragment, listImg: ArrayList<String>?) {
        val fragmentTransactio = supportFragmentManager.beginTransaction()
        fragmentTransactio.add(R.id.fragmentDetail, fragment)
        Toast.makeText(this, "PRUEBA LLAMADA", Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putStringArrayList("key", listImg)
        fragment.arguments = bundle

        fragmentTransactio.commit()
    }

    private fun removeFragment(fragment:Fragment) {
        val fragmentTransactions = supportFragmentManager.beginTransaction()
        fragmentTransactions.remove(fragment)
        fragmentTransactions.commit()
    }

    private fun callFragmentDetall(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentDetail, fragment)

        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if(fragment is ImagesFragment) {
            Toast.makeText(this, "SALIENDO", Toast.LENGTH_LONG).show()
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetail)!!
            removeFragment(fragmento)
            fragment = DetailVehicleFragment()
            callFragmentDetall(fragment)
        }else {
            super.onBackPressed()
        }

    }
}