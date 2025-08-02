/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author javaugi
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageEvent extends BaseEvent {

    private String fileId;
    private String tradeId;
    private FileOperation operation; // UPLOAD, DELETE, UPDATE
    private String filePath;
    private String confirm;

    public FileStorageEvent(String id,
            Instant timestamp,
            String sourceSystem,
            String fileId,
            String tradeId,
            FileOperation operation,
            String filePath,
            String confirm) {
        super(id, timestamp, sourceSystem);
        
        this.fileId = fileId;
        this.tradeId = tradeId;
        this.operation = operation;
        this.filePath = filePath;
        this.confirm = confirm;
    }
}
