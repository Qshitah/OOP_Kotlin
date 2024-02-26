package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime




@Table( "Cart")
data class Cart(
    @Id
    @Column( "cart_id")
    var cartId: Int? = null,

    @Column( "user_id")
    var userId: Long,

    @Column( "product_id")
    var productId: Long,

    @Column( "variant_id")
    var variantId: Int? = null,

    @Column( "quantity")
    var quantity: Int? = null,

    @Column( "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column( "updated_at")
    var updatedAt: LocalDateTime? = null
)

