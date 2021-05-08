package com.app.doordashlite.ui.restaurants

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.doordashlite.R
import com.app.doordashlite.data.Store
import com.app.doordashlite.utils.Utils
import com.bumptech.glide.Glide

class RestaurantAdapter(private val restaurantList: List<Store>) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_view, parent, false)
        return RestaurantViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val context: Context = holder.restaurantImage.context
        val store: Store = restaurantList[position];
        holder.restaurantName.text = store.name
        holder.description.text = store.description

        if(Utils.isPastClosingHours(store.closeTime)) {
            store.status.range_minutes.first().let {
                holder.deliveryTime.text = context.resources.getString(R.string.delivery_time, it)
            }
        } else {
            holder.deliveryTime.text = context.resources.getString(R.string.closed)
        }

        Glide.with(context)
            .load(store.coverImageUrl)
            .into(holder.restaurantImage)
    }

    override fun getItemCount() = restaurantList.size
}

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val restaurantName: TextView = itemView.findViewById(R.id.name)
    val restaurantImage: ImageView = itemView.findViewById(R.id.image)
    val description: TextView = itemView.findViewById(R.id.description)
    val deliveryTime: TextView = itemView.findViewById(R.id.delivery_time)
}