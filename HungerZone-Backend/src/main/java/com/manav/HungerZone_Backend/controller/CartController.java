package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.exception.CartException;
import com.manav.HungerZone_Backend.exception.CartItemException;
import com.manav.HungerZone_Backend.model.Cart;
import com.manav.HungerZone_Backend.model.CartItem;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.AddToCartRequest;
import com.manav.HungerZone_Backend.request.UpdateCartItemRequest;
import com.manav.HungerZone_Backend.service.interfaces.CartService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String token) throws Exception, CartException {

        User user = userService.findUserByJwtToken(token);

        Cart cart = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/item/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestHeader("Authorization") String token,
                                                  @RequestBody AddToCartRequest req) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req ,token);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/item/update")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemRequest req) throws CartItemException {

        CartItem updatedItem = cartService.updateCartItemQuantity(req.getCartId(), req.getQuantity());

        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@RequestHeader("Authorization") String token,
                                                   @RequestParam("id") Long itemId) throws Exception {
        Cart cart = cartService.removeItemFromCart(itemId, token);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Double> calculateCartTotal(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.findCartByUserId(user.getId());

        Double total  = Double.valueOf(cartService.calculateCartTotal(cart));

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @PutMapping("/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.clearCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
