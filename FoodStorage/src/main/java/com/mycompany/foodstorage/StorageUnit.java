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
               throw new StorageFullException("Storage is full. Maximum is 8 items.");
        }
        front = (front - 1 + CAPACITY) % CAPACITY;           //Moves front one position back (wraps around)
        trays[front] = item;                                 //Places the item at the new front
        if (size == 0) {                                     //First item: front and rear are the same
            rear = front;
        }
        size++;                                              //One more item stored
    }

        @Override
    public T removeFront() {                 //Removes the item at the front (LIFO) - O(1)
        if (isEmpty()) {                                     //Nothing to remove
            throw new StorageEmptyException("Storage is empty. Nothing to remove.");
        }
        T item = trays[front];                               //Saves the front item
        trays[front] = null;                                 //Clears the tray
        front = (front + 1) % CAPACITY;                      //Moves front one position forward (wraps around)
        size--;                                              //One less item stored
        return item;                                         //Returns the removed item
    }

    @Override
    public T removeRear() {                  //Removes the item at the rear (FIFO) - O(1)
        if (isEmpty()) {                                     //Nothing to remove
            throw new StorageEmptyException("Storage is empty. Nothing to remove.");
        }
        T item = trays[rear];                                //Saves the rear item
        trays[rear] = null;                                  //Clears the tray
        rear = (rear - 1 + CAPACITY) % CAPACITY;             //Moves rear one position back (wraps around)
        size--;                                              //One less item stored
        return item;                                         //Returns the removed item
    }

    @Override
    public T peekFront() {                   //Shows the front item without removing it - O(1)
        if (isEmpty()) {                                     //Nothing to show
            throw new StorageEmptyException("Storage is empty.");
        }
        return trays[front];                                 //Returns the item at the front
    }

    @Override
    public T peekRear() {                    //Shows the rear item without removing it - O(1)
        if (isEmpty()) {                                     //Nothing to show
            throw new StorageEmptyException("Storage is empty.");
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
            throw new StorageEmptyException("Storage is empty.");
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
