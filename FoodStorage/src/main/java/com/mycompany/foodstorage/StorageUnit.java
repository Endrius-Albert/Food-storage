/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorage;

/**
 *
 * @author endri
 */
public class StorageUnit<T> implements DequeADT<T> {

    private static final int CAPACITY = 8;   //Maximum number of trays
    private final T[] trays;                 //Array that stores the items
    private int front;                        //Position of the front item
    private int rear;                         //Position of the rear item
    private int size;                         //How many items are stored

    @SuppressWarnings("unchecked")
    public StorageUnit() {                   //Creates an empty storage
        trays = (T[]) new Object[CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {               //Checks if the storage is empty - O(1)
        return size == 0;
    }

    @Override
    public boolean isFull() {                //Checks if the storage is full - O(1)
        return size == CAPACITY;
    }

    @Override
    public int size() {                      // Returns how many items are stored - O(1)
        return size;
    }

    @Override
    public void addFront(T item) {           //Adds an item at the front - O(1)
        if (item == null) {                                  //Rejects empty items
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (isFull()) {                                      //Storage already has 8 items
            throw new IllegalStateException("Storage is full."); //replace with StorageFullException
        }
        front = (front - 1 + CAPACITY) % CAPACITY;           //Moves front one position back (wraps around)
        trays[front] = item;                                 //Places the item at the new front
        if (size == 0) {                                     //First item: front and rear are the same
            rear = front;
        }
        size++;                                              //One more item stored
    }

    @Override
    public T removeFront() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T removeRear() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T peekFront() {                   //Shows the front item without removing it - O(1)
        if (isEmpty()) {                                     //Nothing to show
            throw new IllegalStateException("Storage is empty."); //replace with StorageEmptyException
        }
        return trays[front];                                 //Returns the item at the front
    }

    @Override
    public T peekRear() {                    //Shows the rear item without removing it - O(1)
        if (isEmpty()) {                                     //Nothing to show
            throw new IllegalStateException("Storage is empty."); //replace with StorageEmptyException
        }
        return trays[rear];                                  //Returns the item at the rear
    }

    @Override
    public void display() {                  //Shows all items from front to rear - O(n)
        if (isEmpty()) {                                     //No data in the system
            System.out.println("Storage is empty.");
            return;
        }
        System.out.println("Items in storage (front to rear):");
        for (int i = 0; i < size; i++) {                     //Goes through every stored item
            int index = (front + i) % CAPACITY;              //Wraps around the array
            System.out.println((i + 1) + ". " + trays[index]); //Prints position and item
        }
    }

    public int searchByName(String name) {   //Finds the position of a food item by its name - O(n)
        if (name == null || name.trim().isEmpty()) {         //Rejects empty names
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (isEmpty()) {                                     //No data in the system
            throw new IllegalStateException("Storage is empty."); //replace with StorageEmptyException
        }
        for (int i = 0; i < size; i++) {                     //Goes through every stored item, front to rear
            int index = (front + i) % CAPACITY;              //Wraps around the array
            if (trays[index] instanceof FoodItem) {          //Only food items have a name
                FoodItem food = (FoodItem) trays[index];
                if (food.getName().equalsIgnoreCase(name.trim())) { //Ignores upper/lower case
                    return i + 1;                            //Position counted from the front (1 = front)
                }
            }
        }
        return -1;                                           //Not found
    }
}
