package ma.gavilia.admin.service

import ma.gavilia.admin.entity.Order
import ma.gavilia.admin.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(orderRepository: OrderRepository):MainService<Order,Long> (orderRepository) {

    override fun mergeData(existingData: Order, newData: Order): Order {
        existingData.shippingStatus= newData.shippingStatus?: existingData.shippingStatus
        existingData.paymentStatus=newData.paymentStatus?:existingData.paymentStatus
        existingData.status=newData.status?:existingData.status
        return existingData
    }
}