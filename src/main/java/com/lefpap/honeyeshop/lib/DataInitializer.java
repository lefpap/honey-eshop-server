package com.lefpap.honeyeshop.lib;

import com.lefpap.honeyeshop.product.Product;
import com.lefpap.honeyeshop.product_category.ProductCategory;
import com.lefpap.honeyeshop.product_category.ProductCategoryDao;
import com.lefpap.honeyeshop.product.ProductDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;

    @Autowired
    public DataInitializer(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        generateCategories();
        generateProducts();
    }

    private void generateCategories() {
        ProductCategory root1 = ProductCategory.builder()
                .name("Root 1")
                .build();
        ProductCategory root2 = ProductCategory.builder()
                .name("Root 2")
                .build();

        productCategoryDao.save(root1);
        productCategoryDao.save(root2);

        List<ProductCategory> subCategories = List.of(
                ProductCategory.builder()
                        .name("Honey Category 1")
                        .parentCategory(root1)
                        .build(),
                ProductCategory.builder()
                        .name("Honey Category 2")
                        .parentCategory(root1)
                        .build(),
                ProductCategory.builder()
                        .name("Honey Category 3")
                        .parentCategory(root1)
                        .build(),
                ProductCategory.builder()
                        .name("Drink Category 1")
                        .parentCategory(root2)
                        .build(),
                ProductCategory.builder()
                        .name("Drink Category 2")
                        .parentCategory(root2)
                        .build(),
                ProductCategory.builder()
                        .name("Drink Category 3")
                        .parentCategory(root2)
                        .build()
        );

        productCategoryDao.saveAll(subCategories);
    }

    private void generateProducts() {

        List<ProductCategory> categories = productCategoryDao.findAll();
        Random random = new Random(System.currentTimeMillis());

        List<Product> products = List.of(
                Product.builder()
                        .name("Honey 1")
                        .price(BigDecimal.valueOf(10.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(20)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 2")
                        .price(BigDecimal.valueOf(15.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(4)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 3")
                        .price(BigDecimal.valueOf(12.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(56)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 4")
                        .price(BigDecimal.valueOf(8.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(1)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 5")
                        .price(BigDecimal.valueOf(8.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(9)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 6")
                        .price(BigDecimal.valueOf(9.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(14)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 7")
                        .price(BigDecimal.valueOf(20.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(9)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build(),
                Product.builder()
                        .name("Honey 8")
                        .price(BigDecimal.valueOf(18.0))
                        .description("Tastes Good!")
                        .imageUrl("/honey.jpg")
                        .stock(33)
                        .category(categories.get(random.nextInt(categories.size())))
                        .build()
        );

        productDao.saveAll(products);
    }
}
