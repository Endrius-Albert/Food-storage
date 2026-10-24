# Fast-Food Tray Storage – Deque in Java

**CA1 – Algorithms and Architecture** · CCT College Dublin
**Authors:** Endrius Alberto · Willian Bittencourt
**Tech:** Java 17 · Maven · Apache NetBeans 22

---

## Overview

A fast-food restaurant stores five food types (**Burger, Pizza, Fries, Sandwich, Hotdog**) in a storage unit with **two sides** and a maximum of **8 trays**. The chef can work in two ways:

| Mode | How food moves | Behaviour |
|---|---|---|
| **1 – Front side only** | Added and removed at the front | Stack (**LIFO**) |
| **2 – Opposite side** | Added at the front, removed from the opposite side | Queue (**FIFO**) |

Each food item stores its **name**, **weight (g)**, **best-before date** (maximum two weeks) and the **time it was placed** into the storage.

```
             FRONT                                  OPPOSITE SIDE
 addFront →  [ Fries ][ Pizza ][ Burger ][ ][ ][ ][ ][ ]  → removeRear  (FIFO)
 ← removeFront (LIFO)
```

## Data structure: Deque on a circular array

A **Deque (double-ended queue)** was chosen because it is the only structure that supports **both** chef modes:

- a **Stack** cannot remove from the opposite side (fails Mode 2);
- a **Queue** cannot remove from the front (fails Mode 1);
- an **ArrayList** could do both, but adding or removing at the start shifts every element (O(n)).

The Deque is implemented **from scratch** (no `java.util.ArrayDeque`) as a **circular array** of fixed size 8. Two indices, `front` and `rear`, move one step and wrap around with modulo, so no item is ever shifted:

```java
front = (front - 1 + CAPACITY) % CAPACITY;   // addFront
front = (front + 1) % CAPACITY;              // removeFront (LIFO)
rear  = (rear  - 1 + CAPACITY) % CAPACITY;   // removeRear  (FIFO)
```

## Time complexity

| Operation | Method | Big-O | Reason |
|---|---|---|---|
| Add | `addFront(item)` | **O(1)** | front index moves one step |
| Remove (LIFO) | `removeFront()` | **O(1)** | front index moves one step |
| Remove (FIFO) | `removeRear()` | **O(1)** | rear index moves one step |
| Peek / top | `peekFront()` / `peekRear()` | **O(1)** | direct access by index |
| Empty / full / size | `isEmpty()` / `isFull()` / `size()` | **O(1)** | uses the size counter |
| Display | `display()` | **O(n)** | visits every stored item |
| Search | `searchByName(name)` | **O(n)** | linear scan, worst case checks all items |

Space: **O(n)** for a fixed array of 8 trays, with O(1) extra memory per operation.

## Project structure

```
src/main/java/com/mycompany/foodstorage/
├── DequeADT.java               «interface» – contract of the deque operations
├── StorageUnit.java            circular-array Deque (implements DequeADT<T>)
├── FoodItem.java               «abstract» – name, weight, best-before, time added
├── Burger / Pizza / Fries / Sandwich / Hotdog.java   extend FoodItem
├── StorageFullException.java   thrown when a 9th item is added
├── StorageEmptyException.java  thrown when removing/peeking an empty storage
├── InputValidator.java         reads and validates user input
├── Menu.java                   console menu and removal modes
└── FoodStorage.java            main class – starts the application
```

## OOP principles

- **Encapsulation** – all fields are `private` and accessed only through methods and getters.
- **Abstraction** – `DequeADT<T>` defines *what* the storage does; `FoodItem` is abstract.
- **Inheritance** – the five food classes extend `FoodItem`; both exceptions extend `RuntimeException`.
- **Polymorphism** – `StorageUnit<FoodItem>` holds any food subclass; `@Override` implements the interface and `toString()`.

## How to run

1. Clone the repository:
   ```
   git clone https://github.com/Endrius-Albert/Food-storage.git
   ```
2. In **NetBeans**: *File → Open Project* → select the `FoodStorage` folder.
3. Press **F6** (Run). Choose a removal mode, then use the menu:

```
1. Add food item          4. Display all items
2. Remove food item       5. Search food by name
3. Show item at the top   6. Change removal mode
0. Exit
```

## Validation and error handling

| Input / scenario | Behaviour |
|---|---|
| Menu option that is not a number or out of range | Error message, asks again |
| Food type not in the list (e.g. "Taco") or empty | Rejected |
| Weight with letters, decimals, zero or above the limit | Rejected |
| Date in the wrong format or impossible (e.g. 31/02) | Rejected |
| Best-before date in the past or more than 14 days ahead | Rejected |
| Adding a 9th item | "Storage is full. Maximum is 8 items." |
| Remove / peek / display / search with no data | "Storage is empty." |

## Advantages and limitations

**Advantages:** O(1) add and remove at both ends · one structure serves both modes · predictable memory · no element shifting.
**Limitations:** fixed capacity of 8 · search is O(n) · no efficient removal from the middle.

## References

- Goodrich, M.T., Tamassia, R. and Goldwasser, M.H. (2014) *Data Structures and Algorithms in Java*. 6th edn. Hoboken: Wiley.
- Oracle (2021) *Java SE 17 API Specification*. Available at: https://docs.oracle.com/en/java/javase/17/docs/api/
