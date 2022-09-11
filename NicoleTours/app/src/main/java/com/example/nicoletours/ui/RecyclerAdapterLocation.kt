package com.example.nicoletours.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nicoletours.R
import com.example.nicoletours.data.model.LocationModel

class RecyclerAdapterLocation (private val locationList:List<LocationModel>, private val onClickListener:(LocationModel)->Unit):RecyclerView.Adapter<RecyclerViewHolderLocation>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderLocation {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolderLocation(layoutInflater.inflate(R.layout.item_location, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderLocation, position: Int) {
        val item = locationList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = locationList.size
}