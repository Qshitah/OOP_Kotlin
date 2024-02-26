package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Order
import ma.gavilia.admin.service.OrderService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService): MainController<Order,Long>(orderService) {
}