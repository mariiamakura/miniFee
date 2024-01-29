package org.example.finaldemo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderDeliveryFee(@JsonProperty("delivery_fee") val deliveryFee: Int)