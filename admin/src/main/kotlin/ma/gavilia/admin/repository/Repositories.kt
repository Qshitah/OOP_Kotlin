package ma.gavilia.admin.repository

import ma.gavilia.admin.entity.*
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CartRepository:ReactiveCrudRepository<Cart,Int>{

}
interface CategoryRepository:ReactiveCrudRepository<Category,Long>{

}
interface OrderRepository:ReactiveCrudRepository<Order,Long>{

}
interface OrderDetailRepository:ReactiveCrudRepository<OrderDetail,Int>{

}
interface ProductRepository:ReactiveCrudRepository<Product,Long>{

}
interface ProductVariantRepository:ReactiveCrudRepository<ProductVariant,Int>{
    fun findByProductId(productId:Long): Flux<ProductVariant>
}
interface RoleRepository:ReactiveCrudRepository<Role,String>{

    fun findByUserId(userId: Long): Flux<Role>
}
interface ShippingRepository:ReactiveCrudRepository<Shipping,Int>{

}
interface UserRepository:ReactiveCrudRepository<User,Long>{

}