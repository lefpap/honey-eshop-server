package com.lefpap.honeyeshop.lib;

import com.lefpap.honeyeshop.product.Product;
import com.lefpap.honeyeshop.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductDao productDao;

    @Autowired
    public DataInitializer(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Product> products = List.of(
                Product.of("Honey 1", BigDecimal.valueOf(10.0), "Tastes Good!", "/temp.jpg", 20),
                Product.of("Honey 2", BigDecimal.valueOf(15.0), "Tastes Good!", "/temp.jpg", 4),
                Product.of("Honey 3", BigDecimal.valueOf(12.0), "Tastes Good!", "/temp.jpg", 56),
                Product.of("Honey 4", BigDecimal.valueOf(8.0), "Tastes Good!", "/temp.jpg", 1),
                Product.of("Honey 5", BigDecimal.valueOf(8.0), "Tastes Good!", "/temp.jpg", 9),
                Product.of("Honey 6", BigDecimal.valueOf(9.0), "Tastes Good!", "/temp.jpg", 14),
                Product.of("Honey 7", BigDecimal.valueOf(20.0), "Tastes Good!", "/temp.jpg", 9),
                Product.of("Honey 8", BigDecimal.valueOf(18.0), "Tastes Good!", "/temp.jpg", 33)
        );

        productDao.saveAll(products);
    }
}
