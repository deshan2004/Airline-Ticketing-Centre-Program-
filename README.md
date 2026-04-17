# Airline Ticketing Centre Program ✈️

This is a Java-based management system designed for an airline ticketing center. The project demonstrates the evolution from basic procedural programming using arrays to advanced Object-Oriented Programming (OOP) concepts, including a custom Queue implementation for a waiting list.

## 🚀 Features

### Task 1: Arrays Version
- **Console Menu:** Interactive menu (Options 100-999).
- **Core Operations:** View all/empty counters, add/remove passengers.
- **Sorting:** Custom Bubble Sort implementation to sort passengers alphabetically by name.
- **Data Persistence:** Save and Load functionality using parallel arrays and `.txt` file I/O.

### Task 2 & 3: OOP Version
- **Class Architecture:** Implementation using `TicketingCenter`, `Counter`, and `Passenger` classes.
- **Encapsulation:** Passenger details (Surname, First Name, Passport Number, City, and Class) are managed through objects.
- **Maintainability:** Demonstrates why OOP is more scalable than parallel arrays.

### Task 4: Advanced Logic (Waiting List)
- **Custom Queue:** A circular queue implementation (`Passengerqueue`) with a capacity of 10.
- **Automated Dequeue:** When a passenger is removed from a counter (103), the next person in the waiting list is automatically moved to that counter.

## 🛠️ Project Structure

- `AirlineArraysVersion.java`: Implementation using parallel arrays.
- `TicketingCenter.java`: Main entry point for the Class-based version.
- `Counter.java`: Represents individual service counters.
- `Passenger.java`: Model class for passenger data.
- `Passengerqueue.java`: Custom Queue logic for the waiting list.

## 📋 Test Cases

The system has been rigorously tested across three categories:
1.  **Normal Operations:** Adding/Removing passengers (TC-01, TC-02).
2.  **Boundary Cases:** Invalid counter inputs (TC-04) and occupied counter validation (TC-05).
3.  **Advanced Logic:** Waiting list queuing/dequeuing (TC-09, TC-10) and File I/O integrity (TC-07, TC-08).

## 💻 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/airline-ticketing-system.git](https://github.com/your-username/airline-ticketing-system.git)
    ```
2.  **Compile the files:**
    ```bash
    javac ClassesVersion/*.java Arrayversion/*.java
    ```
3.  **Run the Programs:**
    - For Array Version: `java Arrayversion.AirlineArraysVersion`
    - For Class Version: `java ClassesVersion.TicketingCenter`

## 🎓 Academic Info
- **Module:** IT2108-Object Oriented Concepts & Programming
- **Date:** January 2026
