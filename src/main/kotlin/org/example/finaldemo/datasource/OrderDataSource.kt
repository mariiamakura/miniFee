package org.example.finaldemo.datasource

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.example.finaldemo.model.OrderDto

//interfaces are easy to refactor
//interfaces facilitate easier unit testing.

//Interfaces help to enforce the separation of concerns by defining 
//clear boundaries between different parts of the application. 
//OrderService is concerned with business logic related to orders, 
//while OrderDataSource is concerned with data access. 

/**
 * An interface representing a data source for handling orders.
 */
interface OrderDataSource {
    /**
     * Creates an [Order] based on the provided [OrderDto].
     *
     * @param orderDto The data transfer object containing order information.
     * @return The created [Order] instance.
     */
    fun createOrder(orderDto: OrderDto): Order

    /**
     * Calculates the delivery fee for the given [Order].
     *
     * @param order The order for which the delivery fee is calculated.
     * @return The calculated [OrderDeliveryFee].
     */
    fun calculateDelivery(order: Order): OrderDeliveryFee
}