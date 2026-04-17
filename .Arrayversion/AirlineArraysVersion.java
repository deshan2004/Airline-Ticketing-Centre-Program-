package Arrayversion;

import java.util.Scanner;
import java.io.*;// Import this to handle File operations

public class AirlineArraysVersion {

    // Store details in separate lists (Parallel Arrays)
    static String[] firstNames = new String[4];  // Stores First Names
    static String[] surnames = new String[4];// Stores Surnames
    static String[] passports = new String[4];// Stores Passport numbers
    static String[] travelClasses = new String[4];// Stores Class (Economy/Business)
    static boolean[] isOccupied = new boolean[4];// Checks if a counter is full (true/false)

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initialise();// Call the method to set up empty counters
        String option = "";
// Loop to keep the program running until 999 is entered
        do {
            System.out.println("\n Airline Ticketing Centre ");
            System.out.println("100: View all Counters");
            System.out.println("101: View all Empty Counters");
            System.out.println("102: Add Passenger to a Counter");
            System.out.println("103: Remove a Passenger from a Counter");
            System.out.println("104: View Passengers Sorted in alphabetical order");
            System.out.println("105: Store Program Data into file");
            System.out.println("106: Load Program Data from file");
            System.out.println("999: Exit the Program");

            System.out.print("Enter option: ");
            option = sc.next();
// Selection Menu
            switch (option) {
                case "100": viewCounters(); break;
                case "101": viewEmpty(); break;
                case "102": addPassenger(); break;
                case "103": removePassenger(); break;
                case "104": sortPassengers(); break;
                case "105": saveFile(); break;
                case "106": loadFile(); break;
                case "999": System.out.println("Exiting Program... Thank you!"); break;
                default: System.out.println("Invalid Option! Please try again.");
            }
        } while (!option.equals("999"));
    }

    // Set all counters to empty at the start
    static void initialise() {
        for (int i = 0; i < 4; i++) {
            isOccupied[i] = false; //all counters are free
            firstNames[i] = "";
            surnames[i] = "";
            passports[i] = "";
            travelClasses[i] = "";
        }
        System.out.println("Program Initialised successfully.");
    }
    // Displays all counters and shows if they are empty or take
    static void viewCounters() {
        System.out.println("\nCurrent Status ");
        for (int i = 0; i < 4; i++) {
            int displayNum = i + 1; // show 0 index as 1
            if (isOccupied[i]) {
                System.out.println("Counter " + displayNum + ": " + firstNames[i] + " " + surnames[i] + " (Passport: " + passports[i] + " | Class: " + travelClasses[i] + ")");
            } else {
                System.out.println("Counter " + displayNum + ": Empty");
            }
        }
    }

    //show empty counters
    static void viewEmpty() {
        System.out.println("\n Empty Counters");
        for (int i = 0; i < 4; i++) {
            if (!isOccupied[i]) System.out.println("Counter " + (i + 1) + " is available.");
        }
    }

    // Adds a passenger to a counter (1-4)
    static void addPassenger() {
        System.out.print("Enter counter number (1-4): ");
        if (sc.hasNextInt()) {
            int displayNum = sc.nextInt();
            int c = displayNum - 1; // Convert 1-4 input to 0-3 array index

            if (c >= 0 && c < 4) {
                if (!isOccupied[c]) {
                    System.out.print("Enter First Name: "); firstNames[c] = sc.next();
                    System.out.print("Enter Surname: "); surnames[c] = sc.next();
                    System.out.print("Enter Passport Number: "); passports[c] = sc.next();
                    System.out.print("Enter Class (1st/Business/Economy): "); travelClasses[c] = sc.next();
                    isOccupied[c] = true;
                    System.out.println("Passenger successfully added to counter " + displayNum);
                } else {
                    System.out.println("Counter " + displayNum + " is already occupied!");
                }
            } else {
                System.out.println("Error: Valid counters are 1, 2, 3, or 4.");
            }
        } else {
            System.out.println("Invalid input! Please enter a number.");
            sc.next();
        }
    }

    // remove the passenger
    static void removePassenger() {
        System.out.print("Enter counter number to remove (1-4): ");
        if (sc.hasNextInt()) {
            int displayNum = sc.nextInt();
            int c = displayNum - 1;
            if (c >= 0 && c < 4) {
                if (isOccupied[c]) {
                    isOccupied[c] = false;
                    System.out.println("Passenger removed from counter " + displayNum);
                } else {
                    System.out.println("Counter " + displayNum + " is already empty.");
                }
            } else {
                System.out.println("Invalid counter number.");
            }
        }
    }

    // Uses Bubble Sort to arrange names alphabetically
    static void sortPassengers() {
        // Count how many people are there
        int count = 0;
        for (boolean occupied : isOccupied) if (occupied) count++;

        if (count == 0) {
            System.out.println("No passengers to sort.");
            return;
        }
        //Copy names to a temporary array
        String[] tempNames = new String[count];
        int j = 0;
        for (int i = 0; i < 4; i++) {
            if (isOccupied[i]) {
                tempNames[j] = firstNames[i];
                j++;
            }
        }

        // Bubble Sort
        for (int i = 0; i < count - 1; i++) {
            for (int k = 0; k < count - i - 1; k++) {
                // If the current name is alphabetically bigger than the next, swap them
                if (tempNames[k].compareToIgnoreCase(tempNames[k + 1]) > 0) {
                    String temp = tempNames[k];
                    tempNames[k] = tempNames[k + 1];
                    tempNames[k + 1] = temp;
                }
            }
        }

        System.out.println("\nPassengers Sorted by First Name ");
        // Print sorted names
        for (String name : tempNames) System.out.println(name);
    }

    // Saves current array data into a text file
    static void saveFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("airline_data.txt"))) {
            for (int i = 0; i < 4; i++) {
                // Write data as comma-separated values (CSV format)
                pw.println(isOccupied[i] + "," + firstNames[i] + "," + surnames[i] + "," + passports[i] + "," + travelClasses[i]);
            }
            System.out.println("Data saved to airline_data.txt");
        } catch (IOException e) {
            System.out.println("File Save Error: " + e.getMessage());
        }
    }
    //  Load Program Data from file
    static void loadFile() {
        File file = new File("airline_data.txt");// Create a file object
        // Check if the file exists before reading
        if (!file.exists()) {
            System.out.println("No saved data found.");
            return;
        }
        try (Scanner fr = new Scanner(file)) { //Open the file using a Scanner
            int i = 0;
            while (fr.hasNextLine() && i < 4) {
                // Assign each part to the correct parallel array
                String[] data = fr.nextLine().split(",");
                isOccupied[i] = Boolean.parseBoolean(data[0]);
                firstNames[i] = data[1];
                surnames[i] = data[2];
                passports[i] = data[3];
                travelClasses[i] = data[4];
                i++; // Move to the next counter index
            }
            System.out.println("Data loaded from file successfully.");
        } catch (Exception e) {
            // Handle any errors that happen during reading
            System.out.println("File Load Error: " + e.getMessage());
        }
    }
}