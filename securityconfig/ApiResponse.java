package com.ths.mims.securityconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private boolean success;
    private String message;
    private String timestamp;
    private Object result;
    private String[] dtoItems;

    public ApiResponse(int status, String message, boolean success, String[] dtoItems) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.timestamp = Instant.now().toString();
        this.dtoItems = dtoItems;
    }

}
