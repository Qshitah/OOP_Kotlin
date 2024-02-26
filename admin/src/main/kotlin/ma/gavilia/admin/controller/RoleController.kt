package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Role
import ma.gavilia.admin.service.MainService
import ma.gavilia.admin.service.RoleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/roles")
class RoleController(private val roleService: RoleService): MainController<Role,String>(roleService) {

    @GetMapping("/user/{id}")
    fun getUserRoles(@PathVariable id: Long): Flux<Role>{
        return roleService.findByUserId(id)
    }
}