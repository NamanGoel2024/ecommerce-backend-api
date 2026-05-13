package ecommerce_backend_api.service;

import ecommerce_backend_api.entity.Cart;
import ecommerce_backend_api.entity.Product;
import ecommerce_backend_api.entity.User;
import ecommerce_backend_api.repository.CartRepository;
import ecommerce_backend_api.repository.ProductRepository;
import ecommerce_backend_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository) {

        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart addToCart(Long userId,
                          Long productId,
                          int quantity) {

        User user =
                userRepository.findById(userId).orElse(null);

        Product product =
                productRepository.findById(productId).orElse(null);

        Cart cart = new Cart();

        cart.setUser(user);

        cart.setProduct(product);

        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {

        User user =
                userRepository.findById(userId).orElse(null);

        return cartRepository.findByUser(user);
    }
}