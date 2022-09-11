package com.example.nicoletours.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nicoletours.data.model.LocationModel
import com.example.nicoletours.databinding.ItemLocationBinding

class RecyclerViewHolderLocation(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemLocationBinding.bind(view)

    fun render(model: LocationModel, onClickListener:(LocationModel)->Unit){
        binding.tvTitleL.text = model.location;
        Glide.with(binding.imgLocation.context).load(model.img1).into(binding.imgLocation)
        itemView.setOnClickListener{onClickListener(model)}
    }
}