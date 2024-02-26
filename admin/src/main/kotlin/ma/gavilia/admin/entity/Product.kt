package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("Products")
data class Product(
    @Id
    @Column("ProductID")
    var id: Long? = null,

    @Column("Name")
    var name: String?=null,

    @Column("Description")
    var description: String? = null,

    @Column("Price")
    var price: Double?=null,

    @Column("PromotionPrice")
    var promotionPrice: Double? = null,

    @Column("StockQuantity")
    var stockQuantity: Int ?=null,

    @Column("CategoryID")
    var categoryId: Long? = null,

    @Column("Barcode")
    var barcode: String? = null,

    @Column("Images")
    var images: String? = null,

    @Column("CreatedAt")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column("UpdatedAt")
    var updatedAt: LocalDateTime? = null
)
