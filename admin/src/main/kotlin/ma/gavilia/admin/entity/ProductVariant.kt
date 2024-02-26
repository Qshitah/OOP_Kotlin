package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("ProductVariants")
data class ProductVariant(
    @Id
    @Column("VariantID")
    var id: Int? = null,

    @Column("ProductID")
    var productId: Long,

    @Column("Color")
    var color: String? = null,

    @Column("Size")
    var size: String? = null,

    @Column("StockQuantity")
    var stockQuantity: Int ?=null,

    @Column("CreatedAt")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column("UpdatedAt")
    var updatedAt: LocalDateTime? = null
)
