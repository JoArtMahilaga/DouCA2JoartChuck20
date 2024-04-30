/**
 *LinkedList implementation made for Appointment.
 */
public class LinkedList {
    Node head;
    private int size;

    class Node {
        Appointment data;
        Node next;

        Node(Appointment data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
}