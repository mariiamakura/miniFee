package org.example.finaldemo.datasource.repo

import org.example.finaldemo.model.OrderDto
import org.example.finaldemo.model.Order
import org.example.finaldemo.datasource.OrderDataSource
import org.example.finaldemo.model.OrderDeliveryFee
import org.springframework.stereotype.Repository
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.TimeZone

@Repository
class OrderDataSourceRepo : OrderDataSource {
    override fun createOrder(orderDto: OrderDto): Order {
        validateOrderDto(orderDto)

        val order = Order(orderDto.cartValue, orderDto.deliveryDistance, orderDto.numberOfItems, orderDto.time)
        return order
    }

    override fun calculateDelivery(order: Order): OrderDeliveryFee {
        var deliveryFee = 0

        if (order.cartValue >= 20000)
            return OrderDeliveryFee(deliveryFee)
        if (order.cartValue < 1000) {
            deliveryFee = 1000 - order.cartValue
        }
        deliveryFee += calculateFeeForDistance(order.deliveryDistance)
        deliveryFee += calculateItemSurcharge(order.numberOfItems)
        deliveryFee = calculateTimeFee(order.time, deliveryFee)

        if (deliveryFee > 1500)
            return OrderDeliveryFee(1500)
        return OrderDeliveryFee(deliveryFee)
    }

    fun calculateTimeFee(time: String, deliveryFee: Int): Int {
        val instant = Instant.parse(time)
        val orderTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

        val startTimeUTC = LocalTime.of(15, 0)
        val endTimeUTC = LocalTime.of(19, 0)

        if (orderTime.dayOfWeek == DayOfWeek.FRIDAY &&
            orderTime.toLocalTime() in startTimeUTC..endTimeUTC) {
            return (deliveryFee * 1.2).toInt()
        }
        return deliveryFee
    }

    fun calculateItemSurcharge(numOfItems: Int): Int {
        var newNumOfItems = numOfItems
        var fee = 0

        if (newNumOfItems < 5)
            return fee
        if (newNumOfItems > 12)
            fee = 120
        fee += (newNumOfItems - 4) * 50
        return fee
    }

    fun calculateFeeForDistance(distance: Int): Int {
        var totalFee = 200
        var remainingDistance = distance

        while (remainingDistance > 1000) {
            totalFee += 100
            remainingDistance -= 500
        }
        return totalFee
    }

    private fun validateOrderDto(orderDto: OrderDto) {
        if (orderDto.cartValue <= 0 || orderDto.deliveryDistance <= 0 || orderDto.numberOfItems <= 0) {
            throw IllegalArgumentException("Invalid OrderDto. Ensure that 'cartValue', 'deliveryDistance', and 'numberOfItems' are greater than 0.")
        }
        try {
            val instant = Instant.parse(orderDto.time)
            val orderTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))
            val currentUtcTime = LocalDateTime.now(ZoneOffset.UTC)

            if (orderTime.isBefore(currentUtcTime)) {
                throw IllegalArgumentException("Invalid OrderDto. The 'time' should be in the future.")
            }
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Invalid 'time' format")
        }

    }
}