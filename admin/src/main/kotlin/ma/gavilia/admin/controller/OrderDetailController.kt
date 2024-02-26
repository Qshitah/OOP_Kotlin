package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.OrderDetail
import ma.gavilia.admin.service.OrderDetailService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orderDetails")
class OrderDetailController(private val orderDetailService: OrderDetailService): MainController<OrderDetail,Int>(orderDetailService) {
}