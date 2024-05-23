package com.lefpap.honeyeshop.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public String listProducts(@Valid @ModelAttribute ProductsFilters filters, Model model) {
        List<Product> products = productDao.findAll(filters.toSpecification());
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/{id}/details")
    public String viewProductDetails(@PathVariable(name = "id") Long productId, Model model) {
        Product product = productDao.findById(productId).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("product", product);
        return "products/details";
    }
}
