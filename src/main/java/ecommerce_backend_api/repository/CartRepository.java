package ecommerce_backend_api.repository;

import ecommerce_backend_api.entity.Cart;
import ecommerce_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);
}