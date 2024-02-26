package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Shipping
import ma.gavilia.admin.service.ShippingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/shipping")
class ShippingController(private val shippingService: ShippingService): MainController<Shipping,Int>(shippingService) {
}