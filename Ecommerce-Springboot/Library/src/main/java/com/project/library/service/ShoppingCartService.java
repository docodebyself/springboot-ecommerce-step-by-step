package com.project.library.service;

import com.project.library.dto.ProductDto;
import com.project.library.dto.ShoppingCartDto;
import com.project.library.model.Customer;
import com.project.library.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart( ProductDto productDto, int quantity, String username);

    ShoppingCart updateCart( ProductDto productDto, int quantity, String username);

    ShoppingCart removeItemFromCart(ProductDto productDto, String username);

    ShoppingCartDto addItemToCartSession(ShoppingCartDto cartDto, ProductDto productDto, int quantity);

    ShoppingCartDto updateCartSession(ShoppingCartDto cartDto, ProductDto productDto, int quantity);

    ShoppingCartDto removeItemFromCartSession(ShoppingCartDto cartDto, ProductDto productDto, int quantity);

    ShoppingCart combineCart(ShoppingCartDto cartDto, ShoppingCart cart);


    ShoppingCart getCart(String username);
}
