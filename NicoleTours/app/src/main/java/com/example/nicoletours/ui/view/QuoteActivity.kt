package com.example.nicoletours.ui.view

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.nicoletours.ViewPageFragmentAdapter
import com.example.nicoletours.databinding.ActivityQuoteBinding
import com.google.android.material.tabs.TabLayoutMediator

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding
    private val adapter by lazy { ViewPageFragmentAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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