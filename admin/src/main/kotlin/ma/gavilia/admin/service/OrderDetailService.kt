package ma.gavilia.admin.service

import ma.gavilia.admin.entity.OrderDetail
import ma.gavilia.admin.repository.OrderDetailRepository
import org.springframework.stereotype.Service

@Service
class OrderDetailService(orderDetailRepository: OrderDetailRepository):MainService<OrderDetail,Int>(orderDetailRepository) {
    override fun mergeData(existingData: OrderDetail, newData: OrderDetail): OrderDetail {
        existingData.quantity=newData.quantity?:existingData.quantity
        return existingData
    }


}