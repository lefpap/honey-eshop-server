package com.lefpap.honeyeshop.product;

import com.lefpap.honeyeshop.product_category.ProductCategory;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record ProductsFilters(
    String name,
    String category,
    @Positive
    BigDecimal minPrice,
    @Positive
    BigDecimal maxPrice
) {
    public Specification<Product> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.name() != null && !this.name().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name() + "%"));
            }

            if (this.category() != null && !this.category().isEmpty()) {
                Join<Product, ProductCategory> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("name"), this.category()));
            }

            if (this.minPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), this.minPrice()));
            }

            if (this.maxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), this.maxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
