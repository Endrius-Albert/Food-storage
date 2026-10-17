/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

/**
 *
 * @author endri
 */
public class StorageEmptyException extends RuntimeException {  // Error when there is no data in the storage

    public StorageEmptyException(String message) {
        super(message);                                        
    }
   
}
