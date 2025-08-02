/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Product;
import com.spring5.repository.ProductRepository;
import com.spring5.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
//@RequiredArgsConstructor
@RequestMapping("/restproduct")
public class ProductRestController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    // to display all products at localhost:8080
    // to see database values at localhost:8080/h2-console    
    @GetMapping
    public ResponseEntity<Collection<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        //*
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the product doesn't exist
        }
        // */
        //return ResponseEntity.ok(productOptional.orElse(null));
    }
    
    @GetMapping("/restProducts")
    public ResponseEntity<Collection<Product>> listProducts(HttpServletRequest request, ModelMap modelMap) {
        return ResponseEntity.ok(productRepository.findAll());
    } 
    
    @GetMapping("/restProducts2")
    public ResponseEntity<Collection<Product>> listProducts2(org.springframework.http.RequestEntity<Product> request) {
        return ResponseEntity.ok(productRepository.findAll());
    } 

    @PostMapping
    public ResponseEntity<Product> addProduct(org.springframework.http.RequestEntity<Product> request) {
        Product product = productRepository.save(request.getBody());
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(org.springframework.http.RequestEntity<Product> request) {
        Product product = productRepository.save(request.getBody());
        return ResponseEntity.ok(product);
    }
    
    @DeleteMapping("/{id}") // Map DELETE requests to /products/{id}
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id); // Use deleteById for deleting by ID
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the product doesn't exist
        }
    }    
    
    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(org.springframework.http.RequestEntity<Product> request) {  
        Optional<Product> productOptional = Optional.empty();
        long id = 0;
        if (request.getBody() != null) {
            id = request.getBody().getId();            
        }        
        if (id > 0) {
            productOptional = productRepository.findById(id);
        }
        
        if (id > 0 && productOptional.isPresent()) {
            productRepository.deleteById(id); // Use deleteById for deleting by ID
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the product doesn't exist
        }
    }
}
