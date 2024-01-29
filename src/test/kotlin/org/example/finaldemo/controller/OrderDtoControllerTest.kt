package org.example.finaldemo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.finaldemo.model.OrderDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class OrderDtoControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    fun `should return delivery fee OK`() {
        //given
        val validOrderDto = OrderDto(1234, 2222, 3333, "2025-01-15T13:00:00Z")

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderDto)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
            }

    }

    @Test
    fun `should return error, wrong argument`() {
        //given
        val validOrderJson = mapOf(
            "carrtyt_value" to 1234,
            "delivery_distance" to 2222,
            "number_of_items" to 3333,
            "time" to "newTime"
        )

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderJson)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `should return invalid time`() {
        val validOrderJson = mapOf(
            "cart_value" to 1234,
            "delivery_distance" to 2222,
            "number_of_items" to 3333,
            "time" to "208924-01-15T13:00:00Z"
        )

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderJson)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `should return 710 delivery fee no rush hour`() {
        val validOrderJson = mapOf(
            "cart_value" to 790,
            "delivery_distance" to 2235,
            "number_of_items" to 4,
            "time" to "2025-01-15T13:00:00Z"
        )

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderJson)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("delivery_fee") {value("710")}
            }
    }

    @Test
    fun `should return 710 * 1,2 delivery fee for rush hour`() {
        val validOrderJson = mapOf(
            "cart_value" to 790,
            "delivery_distance" to 2235,
            "number_of_items" to 4,
            "time" to "2024-05-03T15:00:00Z"
        )

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderJson)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("delivery_fee") {value("852")}
            }
    }

    @Test
    fun `should return 1500 delivery fee for big order`() {
        val validOrderJson = mapOf(
            "cart_value" to 790,
            "delivery_distance" to 6000,
            "number_of_items" to 10,
            "time" to "2024-05-03T15:00:00Z"
        )

        //when/then
        val performPost = mockMvc.post("/api/order") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(validOrderJson)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("delivery_fee") {value("1500")}
            }
    }
}