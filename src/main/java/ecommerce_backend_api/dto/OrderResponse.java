package ecommerce_backend_api.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;

    private String productName;

    private int quantity;

    private double totalPrice;

    private String status;
}