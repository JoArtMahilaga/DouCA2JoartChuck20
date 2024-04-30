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

    /**
     *adds an appointment to the end of the queue if the appointment's doctor matches the queue's specified doctor.
     */
    @Override
    public void addLast(Appointment appointment) {
        if (!appointment.getDoctorFullName().equalsIgnoreCase(doctorName)) {
            System.out.println("Cannot add appointment: Doctor names do not match.");
            return;
        }

        if (size() >= capacity) {
            System.out.println("Cannot add appointment: Queue is full.");
            return;
        }

        super.addLast(appointment);
        sort();
    }

    /**
     *sorts the linkedlist using insertion sort.
     */
    private void sort() {
        if (isEmpty() || this.head.next == null) {
            return; // No need to sort if the list is empty or has one element
        }

        Node sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            sorted = sortedInsert(sorted, current);
            current = next;
        }
        head = sorted;
    }

    /**
     *sort method that inserts nodes into the correct position in the sorted part of the list.
     */
    private Node sortedInsert(Node sorted, Node newNode) {
        if (sorted == null || newNode.data.compareTo(sorted.data) < 0) {
            newNode.next = sorted;
            return newNode;
        } else {
            Node current = sorted;
            while (current.next != null && newNode.data.compareTo(current.next.data) >= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return sorted;
        }
    }

}