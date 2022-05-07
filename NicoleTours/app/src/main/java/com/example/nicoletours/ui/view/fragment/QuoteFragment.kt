package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.data.network.LocationService
import com.example.nicoletours.data.provider.LocationProvider
import com.example.nicoletours.databinding.FragmentQuoteBinding
import com.example.nicoletours.ui.viewModel.LocationViewModel
import java.time.LocalDate

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




//            locationViewModel.onCreate()


            var lista:ArrayList<String> = ArrayList()
            locationViewModel.locationModel.observe(viewLifecycleOwner, Observer {
                it.forEach{
                    lista.add(it.location)
                    binding.txtRes.text = "hola mundo"
                }
            })

//            locationViewModel.postLocation()

//            var destinos:ArrayList<String> = ArrayList()
//            LocationProvider.getLocation().forEach {
//                destinos.add(it.location)
//            }

            var adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, lista)
            binding.completeDestino.threshold = 0
            binding.completeDestino.setAdapter(adapter)

//            binding.txtRes.text = "hola mundo"
        }

        binding.btnCotizar.setOnClickListener {
            locationViewModel.postLocation()
        }
    }
}
