package org.example.finaldemo.service

import org.example.finaldemo.datasource.OrderDataSource
import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.springframework.stereotype.Service
import org.example.finaldemo.model.OrderDto

@Service
class OrderService(private val orderDataSource: OrderDataSource) {

    fun createOrder(orderDto: OrderDto): Order {
        return orderDataSource.createOrder(orderDto)
    }

    fun calculateDelivery(order: Order): OrderDeliveryFee {
        return orderDataSource.calculateDelivery(order)
    }
    //fun to calculate the delivery fee

    //fun to create and calculate teh delivery fee at once

}