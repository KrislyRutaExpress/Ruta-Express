package com.example.rutaexpress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import entity.Ride

class RideAdapter(private val rides: List<Ride>) : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgRidePhoto: ImageView = itemView.findViewById(R.id.imgRidePhoto_item)
        val txtRestaurantName: TextView = itemView.findViewById(R.id.txtRestaurantName_item)
        val txtCustomerAddress: TextView = itemView.findViewById(R.id.txtCustomerAddress_item)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = rides[position]

        holder.txtRestaurantName.text = ride.restaurantName
        holder.txtCustomerAddress.text = ride.customerAddress
        holder.txtStatus.text = ride.status

        if (ride.photo != null) {
            holder.imgRidePhoto.setImageBitmap(ride.photo)
        } else {
            holder.imgRidePhoto.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

    override fun getItemCount(): Int = rides.size
}
