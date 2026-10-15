/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

/**
 *
 * @author endri
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class FoodItem {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");        //Date format
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //Date and time format

    private final String name;               //Food name 
    private final int weight;                //Weight in grams
    private final LocalDate bestBefore;      //Best-before date
    private final LocalDateTime timeAdded;   //When it was placed into the storage

    public FoodItem(String name, int weight, LocalDate bestBefore) {   //Creates a food item
        this.name = name;
        this.weight = weight;
        this.bestBefore = bestBefore;
        this.timeAdded = LocalDateTime.now();                          //Saves the current time automatically
    }

    public String getName() {                //Returns the food name
        return name;
    }

    public int getWeight() {                 //Returns the weight in grams
        return weight;
    }

    public LocalDate getBestBefore() {       //Returns the best-before date
        return bestBefore;
    }

    public LocalDateTime getTimeAdded() {    //Returns when it was added
        return timeAdded;
    }

    @Override
    public String toString() {               //Shows all the details in one line
        return name + " | " + weight + "g | Best before: " + bestBefore.format(DATE)
                + " | Added: " + timeAdded.format(DATE_TIME);
    }
}
