package org.example.finaldemo.datasource.repo

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderDataSourceRepoTest {
    @Test
    fun `should return order object`() {
        //given
        val orderDto = OrderDto( 790, 2235, 4, "2025-01-15T13:00:00Z")
        val orderDataSourceRepo = OrderDataSourceRepo()

        //when
        val order = orderDataSourceRepo.createOrder(orderDto)

        //then
        assertEquals(790, order.cartValue)
        assertEquals(2235, order.deliveryDistance)
        assertEquals(4, order.numberOfItems)
        assertEquals("2025-01-15T13:00:00Z", order.time)

        println("Created Order: $order")
    }

    @Nested
    inner class deliveryCalculationCheck {
        @Test
        fun `should return 0 delivery fee for cartValue 20000`() {
            //given
            val order = Order(20000, 2235, 4, "2025-01-15T13:00:00Z")
            val orderDataSourceRepo = OrderDataSourceRepo()

            //when
            val deliveryFee = orderDataSourceRepo.calculateDelivery(order)

            //then
            assertEquals(0, deliveryFee.deliveryFee)
            println("Delivery fee calculated: $deliveryFee")
        }

        @Nested
        inner class distanceFeeCheck {
            @Test
            fun `should return 400 fee for 1501 meter distance`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val deliveryFee = orderDataSourceRepo.calculateFeeForDistance(1501)

                //then
                assertEquals(400, deliveryFee)
                println("Delivery fee calculated: $deliveryFee")
            }

            @ParameterizedTest
            @ValueSource(ints = [1499, 1500])
            fun `should return 300 fee for various meter distance`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val deliveryFee = orderDataSourceRepo.calculateFeeForDistance(1500)

                //then
                assertEquals(300, deliveryFee)
                println("Delivery fee calculated: $deliveryFee")
            }
        }
        @Nested
        inner class itemFeeCheck {
            @Test
            fun `should return 300 fee on 10 items`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val fee = orderDataSourceRepo.calculateItemSurcharge(10)

                //then
                assertEquals(300, fee)
                println("Item fee calculated: $fee")
            }

            @Test
            fun `should return 570 fee on 13 items`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val fee = orderDataSourceRepo.calculateItemSurcharge(13)

                //then
                assertEquals(570, fee)
                println("Item fee calculated: $fee")
            }

            @Test
            fun `should return 620 fee on 14 items`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val fee = orderDataSourceRepo.calculateItemSurcharge(14)

                //then
                assertEquals(620, fee)
                println("Item fee calculated: $fee")
            }
        }
        @Nested
        inner class timeFeeCheck {
            @Test
            fun `should 276 fee for friday and 230 charge`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val fee = orderDataSourceRepo.calculateTimeFee("2024-03-08T19:00:00Z", 230)

                //then
                assertEquals(276, fee)
                println("Time fee calculated: $fee")

            }
            @Test
            fun `should 230 fee for not rush friday and 230 charge`() {
                //given
                val orderDataSourceRepo = OrderDataSourceRepo()

                //when
                val fee = orderDataSourceRepo.calculateTimeFee("2024-03-08T19:00:01Z", 230)

                //then
                assertEquals(230, fee)
                println("Time fee calculated: $fee")

            }
        }
    }
}