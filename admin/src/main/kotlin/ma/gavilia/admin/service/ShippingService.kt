package ma.gavilia.admin.service

import ma.gavilia.admin.entity.Shipping
import ma.gavilia.admin.repository.ShippingRepository
import java.time.LocalDateTime

class ShippingService(shippingRepository:ShippingRepository): MainService<Shipping,Int>(shippingRepository) {

    override fun mergeData(existingData: Shipping, newData: Shipping): Shipping {
        // Copy properties from newData to existingData only if they are not null
        existingData.shippingDate = newData.shippingDate ?: existingData.shippingDate
        existingData.address = newData.address ?: existingData.address
        existingData.city = newData.city ?: existingData.city
        existingData.state = newData.state ?: existingData.state
        existingData.zipCode = newData.zipCode ?: existingData.zipCode
        existingData.methodShipping = newData.methodShipping ?: existingData.methodShipping
        existingData.trackNumber = newData.trackNumber ?: existingData.trackNumber
        existingData.country = newData.country ?: existingData.country
        existingData.updatedAt = LocalDateTime.now()
        return existingData

    }
}