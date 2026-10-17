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


public class Sandwich extends FoodItem {              

    public Sandwich(int weight, LocalDate bestBefore) {
        super("Sandwich", weight, bestBefore);         
    }
}