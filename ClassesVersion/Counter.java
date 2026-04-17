package ClassesVersion;

public class Counter {
    private Passenger passenger;// A Counter "has a" Passenger

    public Counter() {
        this.passenger = null;// Initially the counter is empty
    }
    // Add a passenger object to this counter
    public void addPassenger(Passenger p) {
        this.passenger = p;
    }
    // remove  passenger object to this counter
    public void removePassenger() {
        this.passenger = null;
    }
    // Check if the counter is empty
    public boolean isEmpty() {
        return passenger == null;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}