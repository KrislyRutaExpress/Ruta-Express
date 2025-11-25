package datamanager

import entity.User
import entity.Ride

interface DataManager {
    fun addUser(user: User)
    fun getUserById(id: String): User?
    fun addRide(ride: Ride)
    fun getRidesByStatus(status: String): List<Ride>
    fun getAllRides(): List<Ride>
}
