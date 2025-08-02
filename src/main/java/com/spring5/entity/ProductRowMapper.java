/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author javaugi
 */
public class ProductRowMapper implements RowMapper<Product> {
    
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Create a new User object
        Product product = new Product();

        // Map columns from the ResultSet to the User object properties
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price")); 
        product.setQuantity(rs.getInt("quantity"));
        product.setDescription(rs.getString("description"));
        product.setStatus(rs.getBoolean("status"));        

        // Return the mapped User object
        return product;
    }    
}
