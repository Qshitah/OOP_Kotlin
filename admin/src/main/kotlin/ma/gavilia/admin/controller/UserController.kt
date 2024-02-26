package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.User
import ma.gavilia.admin.service.UserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService): MainController<User,Long>(userService) {
}