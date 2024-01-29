package org.example.finaldemo.datasource

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.example.finaldemo.model.OrderDto

interface OrderDataSource {
    fun createOrder(orderDto: OrderDto): Order
    fun calculateDelivery(order: Order): OrderDeliveryFee
}