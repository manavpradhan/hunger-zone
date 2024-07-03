package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.exception.CartException;
import com.manav.HungerZone_Backend.exception.CartItemException;
import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.exception.UserException;
import com.manav.HungerZone_Backend.model.Cart;
import com.manav.HungerZone_Backend.model.CartItem;
import com.manav.HungerZone_Backend.request.AddToCartRequest;

public interface CartService {

    public CartItem addItemToCart(AddToCartRequest req, String userToken) throws Exception, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws UserException;

    public Cart findCartById(Long cartId) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;

}
