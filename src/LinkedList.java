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

    /**
     *checks if the linkedlist is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     *returns the number of elements in the linkedlist.
     */
    public int size() {
        return size;
    }

    /**
     *adds an appointment to the end of the linkedlist.
     */
    public void addLast(Appointment data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

}