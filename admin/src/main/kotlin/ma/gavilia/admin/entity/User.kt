package ma.gavilia.admin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("Users")
data class User(
    @Id
    @Column("UserID")
    var id: Long? = null,

    @Column("Password")
    var password: String?=null, // Il est recommandé d'utiliser un algorithme de hachage sécurisé pour stocker les mots de passe

    @Column("Email")
    var email: String?=null,

    @Column("FirstName")
    var firstName: String? = null,

    @Column("LastName")
    var lastName: String? = null,

    @Column("Phone")
    var phone: String? = null,

    @Column("Profile_Image")
    var profileImage: String? = null,

    @Column("CreatedAt")
    var createdAt: LocalDateTime ?= LocalDateTime.now(),

    @Column("UpdatedAt")
    var updatedAt: LocalDateTime? = null
)
