package com.bastug.order_service.service;

import com.bastug.order_service.dto.APIResponseDto;
import com.bastug.order_service.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long id);

    List<APIResponseDto> getAllOrders();

    List<APIResponseDto> getAllOrdersByUser(Long id);

    void removeByOrderId(Long orderId);
}
