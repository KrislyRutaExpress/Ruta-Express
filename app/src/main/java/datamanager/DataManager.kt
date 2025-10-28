package datamanager

import entity.Ride
import entity.User

interface DataManager {
    fun agregarride(ride: Ride)
    fun obtenerRidesPorEstado(estado: String): List<Ride>
    fun agregarUsuario(usuario: User)
    fun obtenerUsuarioPorId(id: String): User?
}