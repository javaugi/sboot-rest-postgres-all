/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

import com.spring5.RedisConfig;
import com.spring5.entity.Patient;
import com.spring5.repository.PatientRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientDataService {

    private static final String PATIENT_CACHE = "patientCache";

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private @Qualifier(RedisConfig.REDIS_TPL) RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = PATIENT_CACHE, key = "#patientId")
    public Patient getPatientById(Long patientId) throws Exception {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("" + patientId));
    }

    @CachePut(value = PATIENT_CACHE, key = "#patient.id")
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @CacheEvict(value = PATIENT_CACHE, key = "#patientId")
    public void deletePatient(String patientId) {
        patientRepository.deleteById(Long.valueOf(patientId));
    }

    // Bulk operations with pipeline
    public Map<Long, Patient> getMultiplePatients(List<String> patientIds) {
        /*
        return ((List<Patient>)redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            patientIds.forEach(id -> stringRedisConn.get("patientCache::" + id));
            return null;
        }));
        // */

        List<Object> patientsObj = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            patientIds.forEach(id -> stringRedisConn.get("patientCache::" + id));
            return null;
        });

        List<Patient> patientList = patientsObj.stream()
                .filter(Patient.class::isInstance)
                .map(Patient.class::cast)
                .collect(Collectors.toList());

        return patientList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                    Patient::getId,
                    Function.identity()
            ));
    }
    
}
