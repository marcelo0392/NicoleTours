package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.FragmentDetailVehicleBinding
import com.example.nicoletours.ui.viewModel.VehicleViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class DetailVehicleFragment() : Fragment() {

    private var _binding:FragmentDetailVehicleBinding?=null
    private val binding get() = _binding!!

    private val vehicleViewModel:VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vehicleViewModel.getSelectedValue().observe(this, Observer {
            val listaString = ArrayList<String>()
            listaString.add(it.image1)
            listaString.add(it.image2)
//            listaString.add(it.image3)

            val list = mutableListOf<CarouselItem>()
            val carousel:ImageCarousel = binding.carousel
            list.add(CarouselItem(imageUrl = it.image1))
            list.add(CarouselItem(imageUrl = it.image2))
//            list.add(CarouselItem(imageUrl = it.image3))

            carousel.setData(list)
            loadData(it)

            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    vehicleViewModel.postImageSelect(listaString)
                    binding.tvMark.text = carouselItem.imageUrl
                }
            }
        })
    }

    private fun loadData(it:VehicleModel) {
//        binding.tvType.text = it.type
        binding.tvAge.text = it.age.toString()
//        binding.tvModel.text = "Modelo "+it.model
        binding.tvPlaque.text = it.plaque
        binding.tvSeating.text = it.capacity.toString()
    }
}
