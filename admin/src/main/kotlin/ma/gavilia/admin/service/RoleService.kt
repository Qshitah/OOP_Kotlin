package ma.gavilia.admin.service

import ma.gavilia.admin.entity.Order
import ma.gavilia.admin.entity.Role
import ma.gavilia.admin.entity.User
import ma.gavilia.admin.repository.RoleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RoleService(private val roleRepository: RoleRepository):MainService<Role,String> (roleRepository) {

    fun findByUserId(userId: Long): Flux<Role>{
        return roleRepository.findByUserId(userId)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID $userId not found")))

    }
}