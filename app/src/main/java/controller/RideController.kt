package controller

import datamanager.DataManager
import entity.Ride

class RideController(private val dataManager: DataManager) {

    fun crearRide(riDe: Ride) {
        dataManager.agregarRide(riDe)
    }

    fun obtenerRidesPendientes(): List<Ride> {
        return dataManager.obtenerRidesPorEstado("pendiente")
    }
}
