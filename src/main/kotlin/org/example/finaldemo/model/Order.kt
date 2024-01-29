package org.example.finaldemo.model

data class Order constructor(
    val cartValue: Int,
    val deliveryDistance: Int,
    val numberOfItems: Int,
    val time: String
)