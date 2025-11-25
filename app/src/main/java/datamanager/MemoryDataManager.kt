package datamanager

import entity.User
import entity.Ride

class MemoryDataManager private constructor() : DataManager {
    private val users = mutableListOf<User>()
    private val rides = mutableListOf<Ride>()

    companion object {
        @Volatile
        private var instance: MemoryDataManager? = null

        fun getInstance(): MemoryDataManager {
            return instance ?: synchronized(this) {
                instance ?: MemoryDataManager().also { instance = it }
            }
        }
    }

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

    override fun getAllRides(): List<Ride> {
        return rides.toList()
    }
}
