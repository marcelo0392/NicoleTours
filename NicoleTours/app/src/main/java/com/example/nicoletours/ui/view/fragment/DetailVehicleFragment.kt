package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
            val list = mutableListOf<CarouselItem>()
            val carousel:ImageCarousel = binding.carousel
            list.add(CarouselItem(imageUrl = it.image1))
            list.add(CarouselItem(imageUrl = it.image2))
            carousel.setData(list)
            loadData(it)

            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    vehicleViewModel.postImageSelect(listaString)
                }
            }
        })

        binding.btnDetail.setOnClickListener {
            binding.layoutGeneral.isGone = false
            binding.btnDetail.isGone = true
        }
    }

    private fun loadData(it:VehicleModel) {
        binding.tvBrand.text = it.brand
        binding.tvModel.text = it.model
        binding.tvAge.text = it.age.toString()
        binding.tvPlaque.text = it.plaque
        binding.tvSeating.text = it.capacity.toString()
        binding.tvHeating.text = it.heating
        binding.tvCharger.text = it.chargers
        binding.tvTv.text = it.televisions
        binding.tvExtinguishers.text = it.extinguishers
        binding.tvKit.text = it.kit
        binding.tvRadio.text = it.radio
        binding.tvSeating.text = it.capacity.toString() + " Asientos"
        binding.tvAir.text = it.airConditioning
        binding.tvNom.text = it.name +" "+ it.lastName +" "+ it.mLastName
        binding.tvCi.text = it.ci.toString() + " " + it.dpto
        binding.tvRuat.text = it.ruat
        binding.tvSoat.text = it.soat
        binding.tvCategory.text = it.category
        binding.tvRecord.text = it.record
        binding.tvInspection.text = it.inspection + " Nª " + it.numInspection
    }
}
