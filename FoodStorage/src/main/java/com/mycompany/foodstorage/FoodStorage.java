/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.foodstorage;

/**
 *
 * @author endri
 */
public class FoodStorage {

   public static void main(String[] args) {
        StorageUnit<String> storage = new StorageUnit<>();
        storage.addFront("Burger");
        storage.addFront("Pizza");
        storage.addFront("Fries");
        System.out.println("Items stored: " + storage.size()); 
    }
}