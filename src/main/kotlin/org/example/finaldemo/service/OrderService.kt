package org.example.finaldemo.service

import org.example.finaldemo.datasource.OrderDataSource
import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.springframework.stereotype.Service
import org.example.finaldemo.model.OrderDto


//The @Service annotation helps to clarify the role of the annotated class 
//within the application architecture. By using @Service, 
//you indicate that the class contains business logic or 
//performs a specific service-oriented task within your application
@Service
class OrderService(private val orderDataSource: OrderDataSource) {

    fun createOrder(orderDto: OrderDto): Order {
        return orderDataSource.createOrder(orderDto)
    }

    fun calculateDelivery(order: Order): OrderDeliveryFee {
        return orderDataSource.calculateDelivery(order)
    }
}