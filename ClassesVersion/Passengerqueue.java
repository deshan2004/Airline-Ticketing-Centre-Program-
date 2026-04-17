package ClassesVersion;



public class Passengerqueue {
    private Passenger[] queue = new Passenger[10];// Waiting list for 10 people
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    // enQueue: Adding a passenger to the end of the queue
    public void enQueue(Passenger p) {
        if (size < 10) {
            queue[rear] = p;
            rear = (rear + 1) % 10;// Circular Queue logic
            size++;
            System.out.println("Counters full. Passenger added to Waiting List.");
        } else {
            System.out.println("Waiting List is also full!");
        }
    }
    // deQueue: Taking the first passenger out of the queue
    public Passenger deQueue() {
        if (size > 0) {
            Passenger p = queue[front];
            front = (front + 1) % 10;
            size--;
            return p;
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}