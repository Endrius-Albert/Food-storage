/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;
import java.time.LocalDate;
/**
 *
 * @author endri
 */


public class Hotdog extends FoodItem {                 

    public Hotdog(int weight, LocalDate bestBefore) {
        super("Hotdog", weight, bestBefore);           
    }
}
