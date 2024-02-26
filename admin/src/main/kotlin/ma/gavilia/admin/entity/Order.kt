package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table( "Orders")
data class Order(
    @Id
    @Column( "order_id")
    var orderId: Long? = null,

    @Column("user_id")
    var userId: Long,

    @Column("order_date")
    var orderDate: LocalDateTime? = LocalDateTime.now(),

    @Column( "total_amount")
    var totalAmount: Double,

    @Column( "shipping_price")
    var shippingPrice: Double? = 0.0,

    @Column( "shipping_status")
    var shippingStatus: String? = "en cours",

    @Column( "payment_status")
    var paymentStatus: String? = "en cours",

    @Column( "status")
    var status: String? = "en cours",

    @Column( "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column( "updated_at")
    var updatedAt: LocalDateTime? = null
)
