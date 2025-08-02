/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dao;

import com.spring5.entity.Station;
import com.spring5.repository.StationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author javaugi
 */
@Repository
public class StationDao {
    @Autowired
    private StationRepository stationRepository;
    
    public List<Station> saveAll(List<Station> records) {
        return stationRepository.saveAll(records);
    }
    
    public List<Station> findAll() {
        return stationRepository.findAll();
    }
}
