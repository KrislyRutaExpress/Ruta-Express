package controller

import datamanager.DataManager
import entity.Ride

class RideController(private val dataManager: DataManager) {

    fun createRide(ride: Ride) {
        dataManager.addRide(ride)
    }

    fun getPendingRides(): List<Ride> {
        return dataManager.getRidesByStatus("pending")
    }
}
