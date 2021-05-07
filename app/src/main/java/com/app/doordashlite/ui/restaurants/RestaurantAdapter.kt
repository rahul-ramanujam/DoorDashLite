package com.app.doordashlite.ui.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.doordashlite.R
import com.app.doordashlite.data.Store

class RestaurantAdapter(private val restaurantList: List<Store>) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_view, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val store: Store = restaurantList[position];
        holder.restaurantName.text = store.name
    }

    override fun getItemCount() = restaurantList.size
}

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val restaurantName: TextView = itemView.findViewById(R.id.name)
}