package com.example.nicoletours.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nicoletours.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardCotizar.setOnClickListener {
            val i = Intent(this, QuoteActivity::class.java)
            startActivity(i)
        }

        binding.cardList.setOnClickListener {
            val i = Intent(this, ListVehicleActivity::class.java)
            startActivity(i)
        }
    }
}