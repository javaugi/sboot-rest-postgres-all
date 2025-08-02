/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.entity.Product;
import com.spring5.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    private long nextId = 1;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
    
    
    private final Map<Long, Product> products = new HashMap<>();
    public Product saveProduct(Product product) {
        Product newProduct = new Product(nextId++, product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.isStatus());
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }
    
    public static Product createProduct(String name, BigDecimal price, int quantity, String description, boolean status) {
        return Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .description(description)
                .status(status)
                .build();
    }        
}
