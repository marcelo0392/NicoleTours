package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.databinding.FragmentDetailLocationBinding
import com.example.nicoletours.ui.viewModel.LocationViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class DetailLocationFragment : Fragment() {

    private var _binding: FragmentDetailLocationBinding?=null
    private val binding get() = _binding!!

    private val locationViewModel: LocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationViewModel.getSelectedValue().observe(this, Observer {
            val listaString = ArrayList<String>()
            listaString.add(it.img1)
            listaString.add(it.img2)
            val list = mutableListOf<CarouselItem>()
            val carousel: ImageCarousel = binding.carouselLocation
            list.add(CarouselItem(imageUrl = it.img1))
            list.add(CarouselItem(imageUrl = it.img2))
            carousel.setData(list)
            loadData(it)

            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    locationViewModel.postImageSelect(listaString)
                }
            }
        })
    }

    private fun loadData(it: LocationModel) {
        binding.tvTitleLocation.text = it.location.toUpperCase()
        binding.tvAtr1.text = it.atraction1.toUpperCase()
        binding.tvAtr2.text = it.atraction2.toUpperCase()
        binding.tvAtr3.text = it.atraction3.toUpperCase()
        binding.tvAtr4.text = it.atraction4.toUpperCase()
        binding.tvAtr5.text = it.atraction5.toUpperCase()
        binding.tvAtr6.text = it.atraction6.toUpperCase()
        binding.tvAtr7.text = it.atraction7.toUpperCase()
        binding.tvAtr8.text = it.atraction8.toUpperCase()
        binding.tvAtr9.text = it.atraction9.toUpperCase()
        binding.tvAtr10.text = it.atraction10.toUpperCase()
        binding.tvAtr11.text = it.atraction11.toUpperCase()
        binding.tvAtr12.text = it.atraction12.toUpperCase()
        binding.tvAtr13.text = it.atraction13.toUpperCase()
        binding.tvAtr14.text = it.atraction14.toUpperCase()

        if(it.costAtraction1 != 0) binding.tvCostAtr1.text = it.costAtraction1.toString() + " Bs"
        if(it.costAtraction2 != 0) binding.tvCostAtr2.text = it.costAtraction2.toString() + " Bs"
        if(it.costAtraction3 != 0) binding.tvCostAtr3.text = it.costAtraction3.toString() + " Bs"
        if(it.costAtraction4 != 0) binding.tvCostAtr4.text = it.costAtraction4.toString() + " Bs"
        if(it.costAtraction5 != 0) binding.tvCostAtr5.text = it.costAtraction5.toString() + " Bs"
        if(it.costAtraction6 != 0) binding.tvCostAtr6.text = it.costAtraction6.toString() + " Bs"
        if(it.costAtraction7 != 0) binding.tvCostAtr7.text = it.costAtraction7.toString() + " Bs"
        if(it.costAtraction8 != 0) binding.tvCostAtr8.text = it.costAtraction8.toString() + " Bs"
        if(it.costAtraction9 != 0) binding.tvCostAtr9.text = it.costAtraction9.toString() + " Bs"
        if(it.costAtraction10 != 0) binding.tvCostAtr10.text = it.costAtraction10.toString() + " Bs"
        if(it.costAtraction11 != 0) binding.tvCostAtr11.text = it.costAtraction11.toString() + " Bs"
        if(it.costAtraction12 != 0) binding.tvCostAtr12.text = it.costAtraction12.toString() + " Bs"
        if(it.costAtraction13 != 0) binding.tvCostAtr13.text = it.costAtraction13.toString() + " Bs"
        if(it.costAtraction14 != 0) binding.tvCostAtr14.text = it.costAtraction14.toString() + " Bs"
    }
}