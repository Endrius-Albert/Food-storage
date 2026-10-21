/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Willian
 */
public class Menu {

    private final StorageUnit<FoodItem> storage;    //The storage with up to 8 trays
    private final InputValidator validator;         //Reads and validates what the user types
    private boolean lifoMode;                       //true = remove from front (LIFO), false = remove from rear (FIFO)

    public Menu() {                                 //Creates the menu with an empty storage
        storage = new StorageUnit<>();
        validator = new InputValidator(new Scanner(System.in));
    }

    public void start() {                           //Runs the application until the user exits
        System.out.println("=== Fast Food Storage ===");
        chooseMode();
        while (true) {                                       //Keeps showing the menu until option 0
            showOptions();
            int option = validator.readInt("Choose an option: ", 0, 6);
            switch (option) {
                case 1 -> addFood();
                case 2 -> removeFood();
                case 3 -> showTop();
                case 4 -> storage.display();
                case 5 -> searchFood();
                case 6 -> chooseMode();
                case 0 -> {
                    System.out.println("Application closed. Goodbye!");
                    return;                                  //Ends the application
                }
            }
        }
    }

    private void chooseMode() {                     //Chef chooses how food is removed
        System.out.println("\nRemoval mode:");
        System.out.println("1. Front side only (LIFO - last in, first out)");
        System.out.println("2. Opposite side (FIFO - first in, first out)");
        lifoMode = validator.readInt("Choose a mode: ", 1, 2) == 1;
        System.out.println("Mode set to " + getModeName() + ".");
    }

    private String getModeName() {                  //Returns the current mode as text
        return lifoMode ? "LIFO (front side)" : "FIFO (opposite side)";
    }

    private void showOptions() {                    //Prints the main menu
        System.out.println("\n--- Menu | Mode: " + getModeName() + " | Items: " + storage.size() + "/8 ---");
        System.out.println("1. Add food item");
        System.out.println("2. Remove food item");
        System.out.println("3. Show item at the top");
        System.out.println("4. Display all items");
        System.out.println("5. Search food by name");
        System.out.println("6. Change removal mode");
        System.out.println("0. Exit");
    }

    private void addFood() {                        //Reads the details and adds a food item - O(1)
        if (storage.isFull()) {                              //Stops before asking for details
            System.out.println("Storage is full. Maximum is 8 items.");
            return;
        }
        String type = validator.readFoodType();
        int weight = validator.readWeight();
        LocalDate bestBefore = validator.readBestBefore();
        FoodItem food = createFood(type, weight, bestBefore);
        try {
            storage.addFront(food);                          //Food always enters by the front side
            System.out.println(food.getName() + " added to the front.");
        } catch (StorageFullException e) {
            System.out.println(e.getMessage());
        }
    }

    private FoodItem createFood(String type, int weight, LocalDate bestBefore) { //Creates the right subclass
        return switch (type) {
            case "Burger" -> new Burger(weight, bestBefore);
            case "Pizza" -> new Pizza(weight, bestBefore);
            case "Fries" -> new Fries(weight, bestBefore);
            case "Sandwich" -> new Sandwich(weight, bestBefore);
            default -> new Hotdog(weight, bestBefore);
        };
    }

    private void removeFood() {                     //Removes depending on the chosen mode - O(1)
        try {
            FoodItem removed = lifoMode ? storage.removeFront() : storage.removeRear();
            System.out.println("Removed: " + removed);
        } catch (StorageEmptyException e) {                  //No data in the system
            System.out.println(e.getMessage());
        }
    }

    private void showTop() {                        //Shows the next item to be removed - O(1)
        try {
            FoodItem top = lifoMode ? storage.peekFront() : storage.peekRear();
            System.out.println("Item at the top: " + top.getName());
            System.out.println("Details: " + top);
        } catch (StorageEmptyException e) {                  //No data in the system
            System.out.println(e.getMessage());
        }
    }

    private void searchFood() {                     //Finds a food item by name - O(n)
        if (storage.isEmpty()) {                             //No data in the system
            System.out.println("Storage is empty. Nothing to search.");
            return;
        }
        String name = validator.readFoodType();
        int position = storage.searchByName(name);
        if (position == -1) {
            System.out.println(name + " is not in the storage.");
        } else {
            System.out.println(name + " found at position " + position + " (1 = front).");
        }
    }
}