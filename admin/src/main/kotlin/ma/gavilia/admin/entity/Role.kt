package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("Roles")
data class Role(
    @Id
    @Column("Role_Name")
    var roleName: String,

    @Column("Description")
    var description: String? = null
)
