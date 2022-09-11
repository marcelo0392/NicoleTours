package com.example.nicoletours.ui.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityVehicleDetailBinding
import com.example.nicoletours.ui.view.fragment.DetailVehicleFragment
import com.example.nicoletours.ui.view.fragment.ImagesFragment
import com.example.nicoletours.ui.view.fragment.NewVehicleFragment
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

        callFragmentDetall(fragment)
        vehicleViewModel.postVehicleSelect(vehicle)

        vehicleViewModel.getImageSelectedValue().observe(this, Observer {
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetail)!!
            removeFragment(fragmento)
            fragment = ImagesFragment()
            callFragmentImage(fragment, it)

            binding.constrainBar.isGone = true
        })

        binding.btnEdit.setOnClickListener{
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetail)!!
            removeFragment(fragmento)
            fragment = NewVehicleFragment()
            callFragmentDetall(fragment)
            binding.constrainBar.isGone = true

            vehicleViewModel.postVehicleEdit(vehicle)
        }

        binding.btnDelete.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("Se eliminara permanentemente")
            builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { d, i ->
                vehicleViewModel.deleteVehicle(vehicle.plaque)
                Toast.makeText(this, "Elimado", Toast.LENGTH_LONG).show()
                super.onBackPressed()
            })
            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { d, i ->})
            var dialog:AlertDialog = builder.create()
            dialog.show()
        }
    }

    private fun callFragmentImage(fragment: Fragment, listImg: ArrayList<String>?) {
        val fragmentTransactio = supportFragmentManager.beginTransaction()
        fragmentTransactio.add(R.id.fragmentDetail, fragment)

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
        if(fragment is ImagesFragment || fragment is NewVehicleFragment) {
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetail)!!
            removeFragment(fragmento)
            fragment = DetailVehicleFragment()
            callFragmentDetall(fragment)
            binding.constrainBar.isGone = false
        }else {
            super.onBackPressed()
        }
    }
}