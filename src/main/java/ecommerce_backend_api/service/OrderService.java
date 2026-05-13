package ecommerce_backend_api.service;

import ecommerce_backend_api.entity.*;
import ecommerce_backend_api.exception.ResourceNotFoundException;
import ecommerce_backend_api.repository.*;
import org.springframework.stereotype.Service;
import ecommerce_backend_api.exception.ResourceNotFoundException;
import ecommerce_backend_api.dto.OrderResponse;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public OrderResponse convertToOrderResponse(
        OrderEntity order
) {

    OrderResponse response =
            new OrderResponse();

    response.setOrderId(order.getId());

    response.setProductName(
            order.getProduct().getName()
    );

    response.setQuantity(order.getQuantity());

    response.setTotalPrice(
            order.getTotalPrice()
    );

    response.setStatus(order.getStatus());

    return response;
}

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public OrderEntity placeOrder(Long userId,
                                  Long productId,
                                  int quantity) {

        User user =
        userRepository.findById(userId)

        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"
                )
        );

        Product product =
        productRepository.findById(productId)

        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Product not found"
                )
        );

        OrderEntity order = new OrderEntity();

        order.setUser(user);

        order.setProduct(product);

        order.setQuantity(quantity);

        order.setTotalPrice(
                product.getPrice() * quantity
        );

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    public List<OrderResponse> getUserOrders(Long userId) {

    User user =
            userRepository.findById(userId)

            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "User not found"
                    )
            );

    List<OrderEntity> orders =
            orderRepository.findByUser(user);

    return orders.stream()

            .map(this::convertToOrderResponse)

            .toList();
}
}