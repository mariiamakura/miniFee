package org.example.finaldemo.controller

import org.example.finaldemo.model.Order
import org.example.finaldemo.model.OrderDeliveryFee
import org.example.finaldemo.model.OrderDto
import org.example.finaldemo.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleNotReadable(e: HttpMessageNotReadableException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleNotReadable(e: HttpRequestMethodNotSupportedException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.METHOD_NOT_ALLOWED)


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun calculateDelivery(@RequestBody orderDto: OrderDto): OrderDeliveryFee {
        val order = service.createOrder(orderDto)
        return service.calculateDelivery(order)
    }
}