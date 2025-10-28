package util

object Util {
    fun telefonoValido(telefono: String): Boolean {
        return telefono.matches(Regex("\\d{8}"))
    }

    fun generarid(): String {
        return java.util.UUID.randomUUID().toString()
    }
}
