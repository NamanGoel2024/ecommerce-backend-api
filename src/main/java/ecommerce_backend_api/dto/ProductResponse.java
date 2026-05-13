package ecommerce_backend_api.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private double price;
}