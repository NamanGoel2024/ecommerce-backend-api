package ecommerce_backend_api.controller;

import ecommerce_backend_api.entity.Cart;
import ecommerce_backend_api.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {

        return cartService.addToCart(
                userId,
                productId,
                quantity
        );
    }

    @GetMapping("/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) {

        return cartService.getUserCart(userId);
    }
}