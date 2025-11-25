package controller

import datamanager.DataManager
import entity.User

class UserController(private val dataManager: DataManager) {
    fun registerUser(user: User) {
        dataManager.addUser(user)
    }

    fun getUser(id: String): User? {
        return dataManager.getUserById(id)
    }
}
