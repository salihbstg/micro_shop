package com.bastug.cart_service.service.impl;

import com.bastug.cart_service.dto.CartDto;
import com.bastug.cart_service.dto.ProductDto;
import com.bastug.cart_service.dto.UserDto;
import com.bastug.cart_service.entity.Cart;
import com.bastug.cart_service.feign.ProductFeign;
import com.bastug.cart_service.feign.UserFeign;
import com.bastug.cart_service.repository.CartRepository;
import com.bastug.cart_service.service.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserFeign userFeign;
    private final ProductFeign productFeign;

    private Boolean validateUserAndProductExistence(Long userId, Long productId) {
        ProductDto productDto = productFeign.getProductById(productId);
        UserDto userDto = userFeign.getUserById(userId);
        if (userDto.id() != 0 && productDto.id() != 0) {
            return true;
        }
        return false;
    }

    private Boolean validateUserAndProductExistence(Long userId) {

        UserDto userDto = userFeign.getUserById(userId);
        if (userDto.id() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void addProductToCart(Long userId, Long productId) {
        if (validateUserAndProductExistence(userId, productId)) {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteProductInCart(Long userId, Long productId) {
        if (validateUserAndProductExistence(userId, productId)) {
            Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
            cartRepository.delete(cart);
        }
    }

    @Override
    public CartDto getCartByUserId(Long userId) {
        if (validateUserAndProductExistence(userId)) {
            UserDto userDto = userFeign.getUserById(userId);
            List<ProductDto> productDtoList = new ArrayList<>();
            List<Long> productIdList = new ArrayList<>();
            List<Cart> cartElements = cartRepository.findByUserId(userId);
            for (Cart cartElement : cartElements) {
                productIdList.add(cartElement.getProductId());
            }
            productDtoList=productFeign.findProductsByProductIdList(productIdList);
            return new CartDto(userDto, productDtoList);
        }
        return null;
    }
}
