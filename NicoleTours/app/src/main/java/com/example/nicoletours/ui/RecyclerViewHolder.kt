package com.example.nicoletours.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nicoletours.data.model.VehicleModel
import com.example.nicoletours.databinding.ItemVehicleBinding

class RecyclerViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemVehicleBinding.bind(view)

    fun render(model:VehicleModel){
        binding.tvType.text = model.type
        binding.tvAge.text = model.age.toString()
        binding.tvSeating.text = model.seating.toString()
        Glide.with(binding.imgVehicle.context).load(model.image).into(binding.imgVehicle)
    }
}