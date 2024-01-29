package org.example.finaldemo.datasource

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.example.finaldemo.model.OrderDto

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