package Controller

import datamanager.DataManager
import entity.Ride

class RideController(private val dataManager: DataManager) {

    fun crearRide(ride: Ride) {
        dataManager.agregarRide(ride)
    }

    fun obtenerRidesPendientes(): List<Ride> {
        return dataManager.obtenerRidesPorEstado("pendiente")
    }
}
