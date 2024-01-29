package org.example.finaldemo.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class OrderDto @JsonCreator constructor(
    @JsonProperty("cart_value") val cartValue: Int,
    @JsonProperty("delivery_distance") val deliveryDistance: Int,
    @JsonProperty("number_of_items") val numberOfItems: Int,
    @JsonProperty("time") val time: String,
)