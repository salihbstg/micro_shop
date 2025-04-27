package com.bastug.order_service.service.impl;

import com.bastug.order_service.dto.APIResponseDto;
import com.bastug.order_service.dto.CartDto;
import com.bastug.order_service.dto.ProductDto;
import com.bastug.order_service.dto.UserDto;
import com.bastug.order_service.entity.Order;
import com.bastug.order_service.feign.CartFeign;
import com.bastug.order_service.feign.ProductFeign;
import com.bastug.order_service.feign.UserFeign;
import com.bastug.order_service.repository.OrderRepository;
import com.bastug.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartFeign cartFeign;
    private final ProductFeign productFeign;
    private final UserFeign userFeign;

    @Override
    public Order createOrder(Long id) {
        CartDto cartDto=cartFeign.getCart(id);
        if(cartDto!=null){
            Order order = new Order();
            List<Long> productIdList = new ArrayList<>();
            for (ProductDto productDto : cartDto.productDtoList()) {
                productIdList.add(productDto.id());
            }
            order.setProductIds(productIdList);
            order.setCustomerId(cartDto.userDto().id());
            cartFeign.removeAllProductsFromCart(id);
            return orderRepository.save(order);
        }
        else return null;
    }

    @Override
    public List<APIResponseDto> getAllOrders() {
        List<Order> orderList=orderRepository.findAll();
        List<APIResponseDto> apiResponseDtoList=new ArrayList<>();
        for(Order order:orderList){
            List<ProductDto> productDtoList=productFeign.findProductsByProductIdList(order.getProductIds());
            UserDto userDto=userFeign.getUserById(order.getCustomerId());
            apiResponseDtoList.add(new APIResponseDto(order.getId(),new CartDto(userDto,productDtoList)));
        }
        return apiResponseDtoList;
    }

    @Override
    public List<APIResponseDto> getAllOrdersByUser(Long userId) {
        UserDto userDto=userFeign.getUserById(userId);
        List<Order> orderList=orderRepository.findByCustomerId(userId);
        List<APIResponseDto> apiResponseDtoList=new ArrayList<>();
        for(Order order:orderList){
            List<ProductDto> productDtoList=productFeign.findProductsByProductIdList(order.getProductIds());
            apiResponseDtoList.add(new APIResponseDto(order.getId(),new CartDto(userDto,productDtoList)));
        }
        return apiResponseDtoList;
    }

    @Override
    public void removeByOrderId(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
