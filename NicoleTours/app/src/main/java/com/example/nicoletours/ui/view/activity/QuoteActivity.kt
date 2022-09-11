package com.example.nicoletours.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.nicoletours.ViewPageFragmentAdapter
import com.example.nicoletours.databinding.ActivityQuoteBinding
import com.example.nicoletours.ui.viewModel.LocationViewModel
import com.google.android.material.tabs.TabLayoutMediator

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding
    private val adapter by lazy { ViewPageFragmentAdapter(this) }
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationViewModel.onCreate()
        fragmentAdapter()
    }

    private fun fragmentAdapter() {
        binding.pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager,
            TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
                when(position){
                    0 ->{
                        tab.text = "Cotizacion"
                    }
                    1 ->{
                        tab.text = "Detalle"
                    }
                }
            })
        tabLayoutMediator.attach()
    }
}