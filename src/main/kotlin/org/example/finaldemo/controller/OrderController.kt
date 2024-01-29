package org.example.finaldemo.controller

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.example.finaldemo.model.OrderDto
import org.example.finaldemo.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/order")
class OrderController(private val service: OrderService) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIlligalArg(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    fun calculateDelivery(@RequestBody orderDto: OrderDto): OrderDeliveryFee {
//       val deliveryFee = service.createOrder(orderDto).deliveryFee
//        return OrderDeliveryFee(deliveryFee)
//    }

    fun calculateDelivery(@RequestBody orderDto: OrderDto): OrderDeliveryFee {
       val order = service.createOrder(orderDto)
        return service.calculateDelivery(order)
    }
}