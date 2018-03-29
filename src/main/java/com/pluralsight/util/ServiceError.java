/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight.util;

/**
 *
 * @author linzhang
 */
public class ServiceError {
    
    private int code;
    private String message;
    
    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    public ServiceError() {
        
    }
    
    public ServiceError(int code, String message) {
        
        this.code = code;
        this.message = message;
    }
    
    
}
