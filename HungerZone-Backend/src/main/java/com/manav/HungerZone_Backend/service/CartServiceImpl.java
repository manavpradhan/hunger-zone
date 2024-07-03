package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.exception.CartException;
import com.manav.HungerZone_Backend.exception.CartItemException;
import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.model.Cart;
import com.manav.HungerZone_Backend.model.CartItem;
import com.manav.HungerZone_Backend.model.Food;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.repository.CartItemRepository;
import com.manav.HungerZone_Backend.repository.CartRepository;
import com.manav.HungerZone_Backend.repository.FoodRepository;
import com.manav.HungerZone_Backend.request.AddToCartRequest;
import com.manav.HungerZone_Backend.service.interfaces.CartService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodRepository menuRepository;

    @Override
    public CartItem addItemToCart(AddToCartRequest req, String userToken) throws Exception, FoodException, CartException, CartItemException {
        User user = userService.findUserByJwtToken(userToken);

        Optional<Food> menuItem = menuRepository.findById(req.getMenuItemId());
        if(menuItem.isEmpty()) {
            throw new FoodException("Menu Item not exist with id "+req.getMenuItemId());
        }

        Cart cart = cartService.findCartByUserId(user.getId());

        for(CartItem cartItem: cart.getItems()){
            if (cartItem.getFood().equals(menuItem.get())) {

                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(menuItem.get());
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setTotalPrice(req.getQuantity()*menuItem.get().getPrice());

        CartItem savedItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }
        cartItem.get().setQuantity(quantity);
        cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
        return cartItemRepository.save(cartItem.get());
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception, CartException, CartItemException {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = findCartByUserId(user.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }

        cart.getItems().remove(cartItem.get());
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) {
        Long total = 0L;

        for (CartItem cartItem: cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long cartId) throws CartException {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()) {
            return cart.get();
        }
        throw new CartException("Cart not found with the id "+cartId);
    }

    @Override
    public Cart findCartByUserId(Long userId) throws CartException {
        Optional<Cart> cart = cartRepository.findByCustomer_Id(userId);

        if(cart.isPresent()){
            return  cart.get();
        }
        throw new CartException("cart not found");
    }

    @Override
    public Cart clearCart(Long userId) throws CartException {

        Cart cart = findCartByUserId(userId);

        cart.getItems().clear();

        return cartRepository.save(cart);
    }
}
