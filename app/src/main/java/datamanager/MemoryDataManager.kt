package datamanager

import entity.User
import entity.Ride

class MemoryDataManager : DataManager {
    private val users = mutableListOf<User>()
    private val rides = mutableListOf<Ride>()

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }

    override fun addRide(ride: Ride) {
        rides.add(ride)
    }

    override fun getRidesByStatus(status: String): List<Ride> {
        return rides.filter { it.status == status }
    }
}