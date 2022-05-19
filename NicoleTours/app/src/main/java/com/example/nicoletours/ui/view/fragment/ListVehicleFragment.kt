package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentListVehicleBinding
import com.example.nicoletours.ui.RecyclerAdapter
import com.example.nicoletours.ui.viewModel.VehicleViewModel

class ListVehicleFragment : Fragment() {

    private var _binding:FragmentListVehicleBinding?=null
    private val binding get() = _binding!!

    private val vehicleViewModel:VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentListVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lista:ArrayList<VehicleModel> = ArrayList()
        vehicleViewModel.getValue().observe(viewLifecycleOwner, Observer {
            it.forEach{
                lista.add(it)
            }
            initRecycler(lista)
        })
    }

    private fun initRecycler(list: List<VehicleModel>) {
        binding.rvVehicle.layoutManager = LinearLayoutManager(context)
        binding.rvVehicle.adapter = RecyclerAdapter(list)
    }
}