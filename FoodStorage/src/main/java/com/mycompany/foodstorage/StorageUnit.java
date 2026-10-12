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

    private static final int CAPACITY = 8;   // Maximum number of trays
    private final T[] trays;                 // Array that stores the items
    private int front;                        // Position of the front item
    private int rear;                         // Position of the rear item
    private int size;                         // How many items are stored

    @SuppressWarnings("unchecked")
    public StorageUnit() {                   // Creates an empty storage
        trays = (T[]) new Object[CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {               // Checks if the storage is empty - O(1)
        return size == 0;
    }

    @Override
    public boolean isFull() {                // Checks if the storage is full - O(1)
        return size == CAPACITY;
    }

    @Override
    public int size() {                      // Returns how many items are stored - O(1)
        return size;
    }


    @Override
    public void addFront(T item) {
        throw new UnsupportedOperationException("Not implemented yet");
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
    public T peekFront() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T peekRear() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
