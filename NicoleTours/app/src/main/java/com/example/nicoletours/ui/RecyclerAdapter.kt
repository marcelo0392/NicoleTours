package com.example.nicoletours.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nicoletours.R
import com.example.nicoletours.data.model.VehicleModel

class RecyclerAdapter(private val vehicleList:List<VehicleModel>):RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(layoutInflater.inflate(R.layout.item_vehicle, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item =vehicleList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = vehicleList.size
}