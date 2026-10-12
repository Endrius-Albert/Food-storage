package com.mycompany.foodstorage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author endri
 */
public interface DequeADT<T> {

    void addFront(T item);   // Adds an item at the front 
    T removeFront();         // Removes the item at the front (LIFO)
    T removeRear();          // Removes the item at the rear (FIFO)
    T peekFront();           // Shows the front item without removing it 
    T peekRear();            // Shows the rear item without removing it
    boolean isEmpty();       // Checks if the storage is empty 
    boolean isFull();        // Checks if the storage is full
    int size();              // Returns how many items are stored 
    void display();          // Shows all items from front to rear 
}
    

