package ma.gavilia.admin.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("Shipping")
data class Shipping(
    @Column("ShippingID")
    var shippingId: Int? = null,

    @Column("OrderID")
    var orderId: Long,

    @Column("ShippingDate")
    var shippingDate: LocalDateTime? = LocalDateTime.now().plusDays(2),

    @Column("Address")
    var address: String?=null,

    @Column("City")
    var city: String? = null,

    @Column("State")
    var state: String? = null,

    @Column("ZipCode")
    var zipCode: String? = null,

    @Column("MethodShipping")
    var methodShipping: String ?= "AMANA",

    @Column("TrackNumber")
    var trackNumber: String? = null,

    @Column("Country")
    var country: String ?= "Maroc",

    @Column("CreatedAt")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column("UpdatedAt")
    var updatedAt: LocalDateTime? = null
)
