package com.example.nicoletours.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nicoletours.R
import com.example.nicoletours.databinding.FragmentDetailVehicleBinding
import com.example.nicoletours.databinding.FragmentImagesBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val url = args!!.getStringArrayList("key")



        val list = mutableListOf<CarouselItem>()
        val carousel: ImageCarousel = binding.carousel
        list.add(CarouselItem(imageUrl = url!![0]))
        list.add(CarouselItem(imageUrl = url[1]))
//        list.add(CarouselItem(imageUrl = url[2]))

        carousel.setData(list)

//        carousel.carouselListener = object : CarouselListener {
//            override fun onClick(position: Int, carouselItem: CarouselItem) {
//                vehicleViewModel.postImageSelect(listaString)
//                binding.tvMark.text = carouselItem.imageUrl
//            }
//        }

        binding.btnBack.setOnClickListener {
            carousel.previous()
        }
        binding.btnNext.setOnClickListener {
            carousel.next()
        }

//        binding.tv1.text = url!![0].toString()
//        binding.tv2.text = url[1].toString()
//        binding.tv3.text = url[2].toString()
    }
}