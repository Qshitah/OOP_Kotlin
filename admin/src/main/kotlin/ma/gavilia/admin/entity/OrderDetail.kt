package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("OrderDetails")
data class OrderDetail(
    @Id
    @Column("OrderDetailID")
    var orderDetailId: Int? = null,

    @Column("OrderID")
    var orderId: Long,

    @Column("ProductID")
    var productId: Long,

    @Column("VariantID")
    var variantId: Int? = null,

    @Column("Quantity")
    var quantity: Int ?=null,

    @Column("Price")
    var price: Double,

    @Column("CreatedAt")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column("UpdatedAt")
    var updatedAt: LocalDateTime? = null
)
