/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.hackerrank;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.spring5.dao.StationDaoNativeImpl;
import com.spring5.entity.Station;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class StationNativeQueryService implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(StationNativeQueryService.class);

    @Autowired
    private EntityManagerFactory emf;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StationDaoNativeImpl stationDaoNativeImpl;

    @PostConstruct
    public void checkEntities() {
        try {
            System.out.println("EntityManagerFactory    Managed types: " + emf.getMetamodel().getEntities());
            System.out.println("EntityManager           Managed types: " + entityManager.getMetamodel().getEntities());
            System.out.println("SessionFactory          Managed types: " + sessionFactory.getMetamodel().getEntities());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<String> doQuery(String qString) {
        return stationDaoNativeImpl.doQuery(qString);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("StationNativeQueryService with args {}", Arrays.toString(args)); 
        createRecords();
        printRecords();
    }
    
    private void printRecords() {
        List<Station> list = stationDaoNativeImpl.findAll();
        list.stream().forEach(r -> {System.out.println(r);});
    }

    private List<Station> createRecords() {
        List<Station> returnValue = new ArrayList();
        List<StateCities> cities = getData();    
        //List<StateCities> cities = getDataJacksonConverter();
        Station station = null;
        for (int i = 0; i < 20; i++) {
            station = new Station();
            if (cities.size() > i) {
                StateCities cs = cities.get(i);
                station.setCity(cs.getCity());
                station.setState(cs.getState());
            }

            returnValue.add(station);
        }

        returnValue = stationDaoNativeImpl.saveAll(returnValue);
        return returnValue;
    }

    private static List<StateCities> getData() {
        List<StateCities> returnValue = new ArrayList<>();
        StateCities sc = null;

        try (CSVReader reader = new CSVReader(new FileReader(
                StationNativeQueryService.class.getClassLoader().getResource("us_city_state_names.csv").getFile()))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {                
                sc = new StateCities(record);
                System.out.println("line=" + record + "\n" + sc);
                returnValue.add(sc);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        
        return returnValue;
    }
    
    
    private static List<StateCities> getDataJacksonConverter() {
        List<StateCities> returnValue = new ArrayList<>();

        try {
            File csvFile = new File("us_city_state_names.csv");
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.builder()
                    .addColumn("state")
                    .addColumn("stateCode")
                    .addColumn("stateCapital")
                    .addColumn("city")
                    .build().withHeader();
                        
            StateCities sc = null;
            MappingIterator<StateCities> it = mapper.readerFor(StateCities.class)
                    .with(schema)
                    .readValues(csvFile);
            while (it.hasNext()) {
                sc = it.next();
                System.out.println(sc);
                returnValue.add(sc);
            }      
            
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
        return returnValue;
    }

    static class StateCities {
        String[] strArr;
        public StateCities(String[] strArr) {
            this.strArr = strArr;
            if (strArr.length > 0 && strArr[0] != null) {
                this.state = strArr[0].trim();
            }
            if (strArr.length > 1 && strArr[1] != null) {
                this.stateCode = strArr[1].trim();
            }
            if (strArr.length > 2 && strArr[2] != null) {
                this.stateCapital = strArr[2].trim();
            }
            if (strArr.length > 3 && strArr[3] != null) {
                this.city = strArr[3].trim();
            }
        }
        String state;
        String stateCode;
        String stateCapital;
        String city;

        public String[] getStrArr() {
            return strArr;
        }

        public void setStrArr(String[] strArr) {
            this.strArr = strArr;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getStateCapital() {
            return stateCapital;
        }

        public void setStateCapital(String stateCapital) {
            this.stateCapital = stateCapital;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        

        @Override
        public String toString() {
            return "StateCities{" + "state=" + state + ", stateCode=" + stateCode + ", stateCapital=" + stateCapital + ", city=" + city + "\n" + Arrays.toString(strArr) + '}';
        }
        
        
    }

}
