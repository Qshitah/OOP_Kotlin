package ma.gavilia.admin.service

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
abstract class MainService<T : Any, ID : Any>(private val repository: ReactiveCrudRepository<T, ID>) {

    fun getAllData(): Flux<T> {
        return repository.findAll()
    }

    fun getData(id: ID): Mono<T> {
        return repository.findById(id)
            .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with ID $id not found")))
    }

    fun updateData(id: ID, newData: T): Mono<T> {
        return getData(id)
            .flatMap { existingData ->
                val updatedData = mergeData(existingData, newData)
                repository.save(updatedData)
            }
    }

    fun mergeData(existingData: T, newData: T): T {
        // Implement your logic here to merge existingData with newData
        // For example, you might copy properties from newData to existingData
        // This is just a placeholder; you need to replace it with your actual logic
        return existingData // Placeholder logic; replace with your actual merging logic
    }

    fun saveData(data: T): Mono<T> {
        return repository.save(data)
    }

    fun saveMultipleData(data: MutableList<T>): Flux<T> {
        return repository.saveAll(data)
    }

    fun deleteData(id: ID): Mono<Void> {
        return repository.deleteById(id)
    }
}
