package ClassesVersion;

public class Passenger {
    // Encapsulation: Keeping data private
    private String firstName;
    private String surname;
    private String passportNumber;
    private String city;
    private String flightClass;
    // Constructor: To create a new passenger object
    public Passenger(String firstName, String surname, String passport, String city, String flightClass) {
        this.firstName = firstName;
        this.surname = surname;
        this.passportNumber = passport;
        this.city = city;
        this.flightClass = flightClass;
    }
    // Method to get the full name easily
    public String getFullName() {
        return firstName + " " + surname;
    }

    //Retrieving data so that it is easy to save to a file
    public String getRawData() {
        return firstName + "," + surname + "," + passportNumber + "," + city + "," + flightClass;
    }
}