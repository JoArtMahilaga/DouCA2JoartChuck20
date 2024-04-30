/**
 *boundedpriorityqueue for managing appointments.
 */
public class BoundedPriorityQueue extends LinkedList {
    private final int capacity;
    private final String doctorName;

    public BoundedPriorityQueue(int capacity, String doctorName) {
        super();  //calls the LinkedList constructor
        this.capacity = capacity;
        this.doctorName = doctorName.toLowerCase();
    }
}