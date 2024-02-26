package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.ProductVariant
import ma.gavilia.admin.service.ProductVariantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/productVariants")
class ProductVariantController(private val productVariantService: ProductVariantService): MainController<ProductVariant,Int>(productVariantService) {

    @GetMapping("/product/{id}")
    fun getAllDataByProductId(@PathVariable id: Long): Flux<ProductVariant>{
        return productVariantService.findByProductId(id)
    }

}