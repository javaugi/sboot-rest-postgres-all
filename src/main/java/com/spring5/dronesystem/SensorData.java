/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

/**
 *
 * @author javaugi
 */
import java.io.Serializable;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
//@NoArgsConstructor
public class SensorData implements Serializable {

    private final String droneId;
    private final Instant timestamp;
    private final double latitude;
    private final double longitude;
    private final double altitude;
    private final double temperature;
    private final double humidity;
    private final double pressure;
    private final byte[] imageData;
    private final double velocityX;
    private final double velocityY;
    private final double velocityZ;
    private final double batteryLevel;

    public SensorData(String droneId, Instant timestamp, double latitude, double longitude,
            double altitude, double temperature, double humidity, double pressure,
            byte[] imageData, double velocityX, double velocityY, double velocityZ,
            double batteryLevel) {
        this.droneId = droneId;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.imageData = imageData;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.batteryLevel = batteryLevel;
    }

    // Getters omitted for brevity
}
