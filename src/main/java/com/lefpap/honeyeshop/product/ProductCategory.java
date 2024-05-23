package com.lefpap.honeyeshop.product;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
}
