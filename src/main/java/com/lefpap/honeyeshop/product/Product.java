package com.lefpap.honeyeshop.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imageUrl;
    @Column(columnDefinition = "int default 0")
    @Min(value = 0, message = "Stock quantity must be no-negative")
    private Integer stock;
    @Column
    private List<String> tags;
    private Product() {}
    public static Product of(String name, BigDecimal price, String description, String imageUrl, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setStock(stock);

        return product;
    }
}
