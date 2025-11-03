package controller

import datamanager.DataManager
import entity.User

class UserController(private val dataManager: DataManager) {
    fun registrarUsuario(usuario: User) {
        dataManager.agregarUsuario(usuario)
    }

    fun obtenerUsuario(id: String): User? {
        return dataManager.obtenerUsuarioPorId(id)
    }
}