package ma.gavilia.admin.controller

import ma.gavilia.admin.entity.Category
import ma.gavilia.admin.service.MainService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val service:MainService<Category,Long>): MainController<Category,Long>(service) {
}