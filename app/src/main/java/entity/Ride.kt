package entity

import android.graphics.Bitmap

data class Ride(
    val id: String,
    val restaurantName: String,
    val customerAddress: String,
    var status: String, // "pending", "accepted", "delivered"
    val deliveryPersonId: String?,
    var photo: Bitmap? = null
)
