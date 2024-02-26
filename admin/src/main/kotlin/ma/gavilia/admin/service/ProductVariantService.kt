package ma.gavilia.admin.service

import ma.gavilia.admin.entity.ProductVariant
import ma.gavilia.admin.repository.ProductVariantRepository
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

class ProductVariantService(private val productVariantRepository: ProductVariantRepository):MainService<ProductVariant,Int>(productVariantRepository) {

    override fun mergeData(existingData: ProductVariant, newData: ProductVariant): ProductVariant {
        // Copy properties from newData to existingData only if they are not null
        existingData.color = newData.color ?: existingData.color
        existingData.size = newData.size ?: existingData.size
        existingData.stockQuantity = newData.stockQuantity ?: existingData.stockQuantity
        existingData.updatedAt = LocalDateTime.now()
        return existingData
    }

    fun findByProductId(productId:Long):Flux<ProductVariant>{
        return productVariantRepository.findByProductId(productId)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID $productId not found")))
    }
}