/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

import java.util.Scanner;

/**
 *
 * @author Willian
 */
public class InputValidator {

    private static final String[] FOOD_TYPES = {"Burger", "Pizza", "Fries", "Sandwich", "Hotdog"}; //Only foods allowed
    private static final int MIN_WEIGHT = 1;        //Minimum weight in grams
    private static final int MAX_WEIGHT = 5000;     //Maximum weight in grams (5kg per tray)

    private final Scanner scanner;                  //Reads what the user types

    public InputValidator(Scanner scanner) {        //Receives the Scanner from the menu
        this.scanner = scanner;
    }

    public int readInt(String message, int min, int max) {   //Reads a whole number between min and max
        while (true) {                                       //Keeps asking until the input is valid
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(input);        //Converts the text to a number
                if (number >= min && number <= max) {        //Number is inside the allowed range
                    return number;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {              //User typed letters or symbols
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public String readFoodType() {                  //Reads one of the 5 food types
        while (true) {                                       //Keeps asking until the input is valid
            System.out.print("Food type (Burger, Pizza, Fries, Sandwich, Hotdog): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {                           //Rejects empty names
                System.out.println("Food type cannot be empty.");
                continue;
            }
            for (String food : FOOD_TYPES) {                 //Checks the input against every allowed food
                if (food.equalsIgnoreCase(input)) {          //Ignores upper/lower case
                    return food;                             //Returns the name in the standard format
                }
            }
            System.out.println("Invalid food type. Choose one from the list.");
        }
    }

    public int readWeight() {                       //Reads the weight in grams
        return readInt("Weight in grams (" + MIN_WEIGHT + "-" + MAX_WEIGHT + "): ", MIN_WEIGHT, MAX_WEIGHT);
    }
}