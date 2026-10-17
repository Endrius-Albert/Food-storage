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
public class Burger extends FoodItem {                 //A Burger is a type of FoodItem

    public Burger(int weight, LocalDate bestBefore) {
        super("Burger", weight, bestBefore);           //Sends the name and details to FoodItem
    }
}