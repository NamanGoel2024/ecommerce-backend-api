package ecommerce_backend_api.controller;

import ecommerce_backend_api.entity.OrderEntity;
import ecommerce_backend_api.service.OrderService;
import org.springframework.web.bind.annotation.*;
import ecommerce_backend_api.dto.OrderResponse;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public OrderEntity placeOrder(@RequestParam Long userId,
                                  @RequestParam Long productId,
                                  @RequestParam int quantity) {

        return orderService.placeOrder(
                userId,
                productId,
                quantity
        );
    }

    @GetMapping("/{userId}")
    public List<OrderResponse> getUserOrders(
            @PathVariable Long userId) {

        return orderService.getUserOrders(userId);
    }
}