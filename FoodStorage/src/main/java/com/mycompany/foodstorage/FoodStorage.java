/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.foodstorage;
import java.time.LocalDate;
/**
 *
 * @author endri
 */


public class FoodStorage {

    public static void main(String[] args) {
        StorageUnit<FoodItem> storage = new StorageUnit<>();
        storage.addFront(new Burger(250, LocalDate.now().plusDays(5)));
        storage.addFront(new Pizza(500, LocalDate.now().plusDays(3)));
        storage.addFront(new Fries(150, LocalDate.now().plusDays(2)));

        storage.display();
        System.out.println("Front item: " + storage.peekFront().getName());          
        System.out.println("Pizza is at position: " + storage.searchByName("pizza")); 
        System.out.println("Removed (LIFO): " + storage.removeFront().getName());     
        System.out.println("Removed (FIFO): " + storage.removeRear().getName());      
        storage.display();                                                           
    }
}