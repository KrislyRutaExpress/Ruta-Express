package controller

import android.content.Context
import android.widget.Toast
import entity.User

class UserController(private val context: Context) {

    companion object {
        private val userList = mutableListOf<User>()
    }

    fun addUser(user: User) {
        userList.add(user)
        Toast.makeText(context, "User added successfully!", Toast.LENGTH_SHORT).show()
    }

    fun getAllUsers(): List<User> {
        return userList
    }

    fun getUserById(id: String): User? {
        return userList.find { it.id == id }
    }
}