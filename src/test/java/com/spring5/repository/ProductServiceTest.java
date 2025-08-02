/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.repository;

import com.spring5.entity.Product;
import com.spring5.service.ProductServiceImpl;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author javaugi
 */
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
//@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepo;
    
    //When using Mockito Use @InjectMocks to inject Mocked beans to following class
    @InjectMocks
    private ProductServiceImpl productService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testNothing() {        
    }

    //@Test
    public void testGetAllProducts() {
        //given
        long id = 1;
        long id2 = 2;
        Product product = new Product(id, "Soap");
        Product product2 = new Product(id2, "Mirror");
        //When
        given(this.productRepo.findAll())
                .willReturn(List.of(product, product2));
        List<Product> productList = productService.findAll();
        //Then
        //Make sure to import assertThat From org.assertj.core.api package
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }
}
