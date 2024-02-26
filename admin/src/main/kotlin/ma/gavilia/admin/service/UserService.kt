package ma.gavilia.admin.service

import ma.gavilia.admin.entity.User
import ma.gavilia.admin.repository.UserRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(userRepository: UserRepository) : MainService<User, Long>(userRepository) {

    override fun mergeData(existingData: User, newData: User): User {
        // Copy properties from newData to existingData only if they are not null
        existingData.email = newData.email ?: existingData.email
        existingData.password = newData.password ?: existingData.password
        existingData.firstName = newData.firstName ?: existingData.firstName
        existingData.lastName = newData.lastName ?: existingData.lastName
        existingData.phone = newData.phone ?: existingData.phone
        existingData.profileImage = newData.profileImage ?: existingData.profileImage
        existingData.updatedAt = LocalDateTime.now()
        return existingData
    }
}