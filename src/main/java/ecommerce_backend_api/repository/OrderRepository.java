package ecommerce_backend_api.repository;

import ecommerce_backend_api.entity.OrderEntity;
import ecommerce_backend_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUser(User user);
}