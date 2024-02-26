package ma.gavilia.admin.service

import ma.gavilia.admin.entity.Product
import ma.gavilia.admin.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
@Service
class ProductService(productRepository: ProductRepository): MainService<Product,Long>(productRepository) {

    override fun mergeData(existingData: Product, newData: Product): Product {
        existingData.name = newData.name ?: existingData.name
        existingData.description = newData.description ?: existingData.description
        existingData.price = newData.price ?: existingData.price
        existingData.promotionPrice = newData.promotionPrice ?: existingData.promotionPrice
        existingData.stockQuantity = newData.stockQuantity ?: existingData.stockQuantity
        existingData.categoryId = newData.categoryId ?: existingData.categoryId
        existingData.barcode = newData.barcode ?: existingData.barcode
        existingData.images = newData.images ?: existingData.images
        existingData.updatedAt = LocalDateTime.now()
        return existingData
    }
}