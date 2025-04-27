package com.bastug.order_service.controller;

import com.bastug.order_service.dto.APIResponseDto;
import com.bastug.order_service.entity.Order;
import com.bastug.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity<Order> createOrder(@PathVariable(name = "id") Long id) {
        Order order=orderService.createOrder(id);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping
    public ResponseEntity<List<APIResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<APIResponseDto>> getOrdersById(@PathVariable(name = "userId") long userId) {
        return ResponseEntity.ok(orderService.getAllOrdersByUser(userId));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "orderId") long orderId) {
        orderService.removeByOrderId(orderId);
        return ResponseEntity.status(200).body("Order deleted");
    }

}
