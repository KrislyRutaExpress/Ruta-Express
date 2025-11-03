package entity

class Ride(
    val id: String,
    val nombreRestaurante: String,
    val direccionCliente: String,
    var estado: String, // "pendiente", "aceptado", "entregado"
    val repartidorId: String?
)
