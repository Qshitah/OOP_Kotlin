package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Cart
import ma.gavilia.admin.service.CartService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/carts")
class CartController(private val cartService: CartService): MainController<Cart,Int>(cartService) {
}