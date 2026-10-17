/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

/**
 *
 * @author endri
 */
public class StorageFullException extends RuntimeException {   //Error when the storage has 8 items

    public StorageFullException(String message) {
        super(message);                                        
    }
}