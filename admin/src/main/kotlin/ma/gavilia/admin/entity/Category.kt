package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("Categories")
data class Category(
    @Id
    @Column("CategoryID")
    var id:Long?=null,

    @Column("nomCategory")
    var nom:String,
    @Column("parentCategoryId")
    var parentCategoryId:Long?=null,
)
