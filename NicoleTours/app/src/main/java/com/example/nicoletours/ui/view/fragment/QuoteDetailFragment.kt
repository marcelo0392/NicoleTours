package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.databinding.FragmentQuoteDetailBinding
import com.example.nicoletours.ui.viewModel.LocationViewModel

class QuoteDetailFragment : Fragment() {

    private var _binding:FragmentQuoteDetailBinding?=null
    private val binding get() = _binding!!

    private val locationViewModel: LocationViewModel by activityViewModels()

//    companion object {
//        private const val ARG_OBJECT = "objeto"
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentQuoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
////            binding.textView2.text = "Fragmento " + getInt(ARG_OBJECT).toString()
//        }
        val lista:ArrayList<LocationModel> = ArrayList()
        locationViewModel.getValue().observe(viewLifecycleOwner, Observer {
            it.forEach{
                lista.add(it)
//                binding.textView11.text = it.location
            }
        })

        locationViewModel.getLocationSelected().observe(viewLifecycleOwner, Observer {
            cleanCost()
            cleanAtraction()
            val location = it
            lista.forEach {
                if(it.location == location){
                    binding.tvAtraction1.text = it.atraction1
                    binding.tvAtraction2.text = it.atraction2
                    binding.tvAtraction3.text = it.atraction3
                    binding.tvAtraction4.text = it.atraction4
                    binding.tvAtraction5.text = it.atraction5
                    binding.tvAtraction6.text = it.atraction6
                    binding.tvAtraction7.text = it.atraction7
                    binding.tvAtraction8.text = it.atraction8
                    binding.tvAtraction9.text = it.atraction9
                    binding.tvAtraction10.text = it.atraction10
                    binding.tvAtraction11.text = it.atraction11
                    binding.tvAtraction12.text = it.atraction12
                    binding.tvAtraction13.text = it.atraction13
                    binding.tvAtraction14.text = it.atraction14

                    if (it.costAtraction1 != 0) binding.tvCost1.text = it.costAtraction1.toString()
                    if (it.costAtraction2 != 0) binding.tvCost2.text = it.costAtraction2.toString()
                    if (it.costAtraction3 != 0) binding.tvCost3.text = it.costAtraction3.toString()
                    if (it.costAtraction4 != 0) binding.tvCost4.text = it.costAtraction4.toString()
                    if (it.costAtraction5 != 0) binding.tvCost5.text = it.costAtraction5.toString()
                    if (it.costAtraction6 != 0) binding.tvCost6.text = it.costAtraction6.toString()
                    if (it.costAtraction7 != 0) binding.tvCost7.text = it.costAtraction7.toString()
                    if (it.costAtraction8 != 0) binding.tvCost8.text = it.costAtraction8.toString()
                    if (it.costAtraction9 != 0) binding.tvCost9.text = it.costAtraction9.toString()
                    if (it.costAtraction10 != 0) binding.tvCost10.text = it.costAtraction10.toString()
                    if (it.costAtraction11 != 0) binding.tvCost11.text = it.costAtraction11.toString()
                    if (it.costAtraction12 != 0) binding.tvCost12.text = it.costAtraction12.toString()
                    if (it.costAtraction13 != 0) binding.tvCost13.text = it.costAtraction13.toString()
                    if (it.costAtraction14 != 0) binding.tvCost14.text = it.costAtraction14.toString()
                }
            }
        })
    }

    private fun cleanAtraction() {
        binding.tvAtraction1.text = null
        binding.tvAtraction1.isGone
        binding.tvAtraction2.text = null
        binding.tvAtraction2.isGone
        binding.tvAtraction3.text = null
        binding.tvAtraction3.isGone
        binding.tvAtraction4.text = null
        binding.tvAtraction4.isGone
        binding.tvAtraction5.text = null
        binding.tvAtraction5.isGone
        binding.tvAtraction6.text = null
        binding.tvAtraction6.isGone
        binding.tvAtraction7.text = null
        binding.tvAtraction7.isGone
        binding.tvAtraction8.text = null
        binding.tvAtraction8.isGone
        binding.tvAtraction9.text = null
        binding.tvAtraction9.isGone
        binding.tvAtraction10.text = null
        binding.tvAtraction10.isGone
        binding.tvAtraction11.text = null
        binding.tvAtraction11.isGone
        binding.tvAtraction12.text = null
        binding.tvAtraction12.isGone
        binding.tvAtraction13.text = null
        binding.tvAtraction13.isGone
        binding.tvAtraction14.text = null
        binding.tvAtraction14.isGone
    }

    private fun cleanCost() {
        binding.tvCost1.text = null
        binding.tvCost1.isGone
        binding.tvCost2.text = null
        binding.tvCost2.isGone
        binding.tvCost3.text = null
        binding.tvCost3.isGone
        binding.tvCost4.text = null
        binding.tvCost4.isGone
        binding.tvCost5.text = null
        binding.tvCost5.isGone
        binding.tvCost6.text = null
        binding.tvCost6.isGone
        binding.tvCost7.text = null
        binding.tvCost7.isGone
        binding.tvCost8.text = null
        binding.tvCost8.isGone
        binding.tvCost9.text = null
        binding.tvCost9.isGone
        binding.tvCost10.text = null
        binding.tvCost10.isGone
        binding.tvCost11.text = null
        binding.tvCost11.isGone
        binding.tvCost12.text = null
        binding.tvCost12.isGone
        binding.tvCost13.text = null
        binding.tvCost13.isGone
        binding.tvCost14.text = null
        binding.tvCost14.isGone
    }
}