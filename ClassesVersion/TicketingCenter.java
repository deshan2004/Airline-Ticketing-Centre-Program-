package ClassesVersion;

import java.util.Scanner;
import java.io.*;// Import this to handle File operations

public class TicketingCenter {
    // Create an array to hold 4 Counter objects
    static Counter[] counters = new Counter[4];
    // Create a Waiting List (Queue) for extra passengers
    static Passengerqueue waitingList = new Passengerqueue(); // Task 04 Queue
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the counters
        for (int i = 0; i < 4; i++) {
            counters[i] = new Counter();
        }

        String option = "";
        do {
            // Loop to keep the program running until 999 is entered
            System.out.println("\n Airline Ticketing Centre ");
            System.out.println("100: View all Counters");
            System.out.println("101: View Empty Counters");
            System.out.println("102: Add Passenger");
            System.out.println("103: Remove Passenger");
            System.out.println("104: Sort Passengers");
            System.out.println("105: Store Data");
            System.out.println("106: Load Data");
            System.out.println("999: Exit");

            System.out.print("Enter option: ");
            option = sc.next();
// Selection Menu
            switch (option) {
                case "100": viewAll(); break;
                case "101": viewEmpty(); break;
                case "102": addPassenger(); break;
                case "103": removePassenger(); break;
                case "104": sortPassengers(); break;
                case "105": storeData(); break;
                case "106": loadData(); break;
                case "999": System.out.println("Exiting..."); break;
                default: System.out.println("Invalid Option");
            }
        } while (!option.equals("999"));
    }
    // Add a Passenger
    static void addPassenger() {
        // Check if there is an empty counter
        int freeCounterIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (counters[i].isEmpty()) {
                freeCounterIndex = i; // Found an empty spot
                break; // Stop looking after finding the first one
            }
        }

        //  Get Passenger Details from the user
        System.out.print("First Name: "); String fName = sc.next();
        System.out.print("Surname: "); String sName = sc.next();
        System.out.print("Passport: "); String pass = sc.next();
        System.out.print("City: "); String city = sc.next();
        System.out.print("Enter Class (1st/Bus/Eco): "); String fClass = sc.next();
// Create a new Passenger object using the constructor
        Passenger p = new Passenger(fName, sName, pass, city, fClass);

        if (freeCounterIndex != -1) {
            // If a counter is free, add the passenger there
            counters[freeCounterIndex].addPassenger(p);
            System.out.println("Passenger added to Counter " + (freeCounterIndex + 1));
        } else {
            // If all counters are full, add to Waiting List
            System.out.println("All counters are full!");
            waitingList.enQueue(p);
        }
    }
    // View all counters
    static void viewAll() {
        System.out.println("\n Counter Status ");
        for (int i = 0; i < 4; i++) {
            // Use ternary operator to check if empty or not
            String status = counters[i].isEmpty() ? "Empty" : counters[i].getPassenger().getFullName();
            System.out.println("Counter " + (i + 1) + " : " + status);
        }
    }
    // View only empty counters
    static void viewEmpty() {
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            if (counters[i].isEmpty()) {
                System.out.println("Counter " + (i + 1) + " is available.");
                found = true;
            }
        }
        if (!found) System.out.println("All counters are occupied!");
    }
    // Remove Passenger
    static void removePassenger() {
        System.out.print("Enter counter number to remove (1-4): ");
        if (sc.hasNextInt()) {
            int displayNum = sc.nextInt();
            int index = displayNum - 1; // Convert 1-4 to 0-3 index

            if (index >= 0 && index < 4) {
                if (!counters[index].isEmpty()) {
                    //  Remove the current passenger
                    Passenger removed = counters[index].getPassenger();
                    counters[index].removePassenger();
                    System.out.println(removed.getFullName() + " removed from Counter " + displayNum);

                    // Check Waiting List
                    if (!waitingList.isEmpty()) {
                        // Get the next person from the Queue
                        Passenger nextInLine = waitingList.deQueue();
                        // Add them to the empty counter immediately
                        counters[index].addPassenger(nextInLine);
                        System.out.println(" Waiting List Update ");
                        System.out.println("Passenger " + nextInLine.getFullName() + " moved from Queue to Counter " + displayNum);
                    }
                } else {
                    System.out.println("Counter " + displayNum + " is already empty.");
                }
            } else {
                System.out.println("Error: Valid counters are 1-4.");
            }
        } else {
            System.out.println("Invalid input.");
            sc.next();
        }
    }
  // Sort Passengers using Bubble Sort
    static void sortPassengers() {
       // Count how many passengers exist
        int count = 0;
        for (Counter c : counters) if (!c.isEmpty()) count++;

        if (count == 0) {
            System.out.println("No passengers to sort.");
            return;
        }
       // Copy names to a temporary array
        String[] names = new String[count];
        int j = 0;
        for (Counter c : counters) {
            if (!c.isEmpty()) names[j++] = c.getPassenger().getFullName();
        }

        // Bubble Sort
        for (int i = 0; i < names.length - 1; i++) {
            for (int k = 0; k < names.length - i - 1; k++) {
                if (names[k].compareToIgnoreCase(names[k+1]) > 0) {
                    // Swap logic
                    String temp = names[k];
                    names[k] = names[k+1];
                    names[k+1] = temp;
                }
            }
        }
        System.out.println("\n Passengers Sorted by Name ");
        for (String n : names) System.out.println(n);
    }
    //Store data to a file
    static void storeData() {
        try (PrintWriter out = new PrintWriter(new FileWriter("airline_data_class.txt"))) {
            for (Counter c : counters) {
                if (!c.isEmpty()) out.println(c.getPassenger().getRawData());
                else out.println("empty");
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) { System.out.println("Error saving file."); }
    }
    //Load data from file
    static void loadData() {
        File file = new File("airline_data_class.txt");
        if(!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                if (line != null && !line.equals("empty")) {
                    String[] d = line.split(",");
                    // Create a new Passenger and add to counter
                    counters[i].addPassenger(new Passenger(d[0], d[1], d[2], d[3], d[4]));
                } else {
                    counters[i].removePassenger();
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) { System.out.println("Error loading file."); }
    }
}