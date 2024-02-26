package ma.gavilia.admin.service

import ma.gavilia.admin.entity.Cart
import ma.gavilia.admin.repository.CartRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
@Service
class CartService(cartRepository: CartRepository): MainService<Cart,Int>(cartRepository) {

    override fun mergeData(existingData: Cart, newData: Cart): Cart {
        existingData.quantity = newData.quantity ?: existingData.quantity
        existingData.updatedAt = LocalDateTime.now()
        return existingData
    }
}