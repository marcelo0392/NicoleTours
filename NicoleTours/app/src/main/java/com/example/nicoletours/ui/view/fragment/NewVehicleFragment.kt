package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nicoletours.databinding.FragmentNewVehicleBinding

class NewVehicleFragment : Fragment() {

    private var _binding: FragmentNewVehicleBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }



}