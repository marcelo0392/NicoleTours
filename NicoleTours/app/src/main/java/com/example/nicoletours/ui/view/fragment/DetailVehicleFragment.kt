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
            val list = mutableListOf<CarouselItem>()
            val carousel:ImageCarousel = binding.carousel
            list.add(
                CarouselItem(
                    imageUrl = it.image
                )
            )

            carousel.setData(list)
            binding.tvType.text = it.type
            binding.tvAge.text = it.age.toString()
            binding.tvModel.text = it.model
            binding.tvPlaque.text = it.plaque
            binding.tvSeating.text = it.seating.toString()

            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
//                    binding.imgVehicle.isVisible = true
//                    Glide.with(binding.imgVehicle.context).load(carouselItem.imageUrl).into(binding.imgVehicle)
                    binding.tvMark.text = carouselItem.imageUrl
                }
            }
        })

    }
}
