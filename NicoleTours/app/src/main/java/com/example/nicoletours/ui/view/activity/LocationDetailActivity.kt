package com.example.nicoletours.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nicoletours.R
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ActivityLocationDetailBinding
import com.example.nicoletours.ui.view.fragment.DetailLocationFragment
import com.example.nicoletours.ui.view.fragment.ImagesFragment
import com.example.nicoletours.ui.viewModel.LocationViewModel

class LocationDetailActivity : AppCompatActivity() {

    private val locationViewModel:LocationViewModel by viewModels()
    private lateinit var binding:ActivityLocationDetailBinding
    var fragment: Fragment = DetailLocationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val location = intent.getSerializableExtra("location") as LocationModel

        callFragmentDetall(fragment)
        locationViewModel.postLocationItemSelected(location)

        locationViewModel.getImageSelectedValue().observe(this, Observer {
            val fragmento = supportFragmentManager.findFragmentById(R.id.fragmentDetailLocation)!!
            removeFragment(fragmento)
            fragment = ImagesFragment()
            callFragmentImage(fragment, it)

            binding.constrainBar.isGone = true
        })
    }

    private fun callFragmentDetall(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentDetailLocation, fragment)
        fragmentTransaction.commit()
    }

    private fun removeFragment(fragment:Fragment) {
        val fragmentTransactions = supportFragmentManager.beginTransaction()
        fragmentTransactions.remove(fragment)
        fragmentTransactions.commit()
    }

    private fun callFragmentImage(fragment: Fragment, listImg: ArrayList<String>?) {
        val fragmentTransactio = supportFragmentManager.beginTransaction()
        fragmentTransactio.add(R.id.fragmentDetailLocation, fragment)

        val bundle = Bundle()
        bundle.putStringArrayList("key", listImg)
        fragment.arguments = bundle
        fragmentTransactio.commit()
    }
}