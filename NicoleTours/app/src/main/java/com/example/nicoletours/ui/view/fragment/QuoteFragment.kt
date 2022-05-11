package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nicoletours.databinding.FragmentQuoteBinding
import com.example.nicoletours.ui.viewModel.LocationViewModel

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private val locationViewModel:LocationViewModel by activityViewModels()

    companion object {
        private const val ARG_OBJECT = "objeto"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            binding.textView1.text = "Fragmento " + getInt(ARG_OBJECT).toString()

            var lista:ArrayList<String> = ArrayList()
            locationViewModel.getValue().observe(viewLifecycleOwner, Observer {
                it.forEach{
                    lista.add(it.location)
                    binding.txtRes.text = "hola mundo"
                }
            })

            location(view, lista)
            vehicles(view)
            persons(view)
            days(view)
        }
    }

    private fun persons(view: View) {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completePasajeros.threshold = 0
        binding.completePasajeros.setAdapter(adapter)
    }

    private fun location(view: View, lista:ArrayList<String>) {
        var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, lista)
        binding.completeDestino.threshold = 0
        binding.completeDestino.setAdapter(adapter)
    }

    private fun vehicles(view: View) {
        val list = listOf<String>("Surubi", "Trufi", "Vagoneta", "Flota")
        var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeVehiculo.threshold = 0
        binding.completeVehiculo.setAdapter(adapter)
    }

    private fun days(view: View){
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, list)
        binding.completeDias.threshold = 0
        binding.completeDias.setAdapter(adapter)
    }
}
