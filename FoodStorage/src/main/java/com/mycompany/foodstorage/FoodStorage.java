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

        System.out.println("Removed from front: " + storage.removeFront());  
        System.out.println("Removed from rear: " + storage.removeRear());    
        System.out.println("Items left: " + storage.size());                        
    }
}