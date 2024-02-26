package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Product
import ma.gavilia.admin.service.MainService
import ma.gavilia.admin.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService): MainController<Product,Long>(productService) {
}