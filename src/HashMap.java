import java.util.LinkedList;

/**
 * A implementation of a hash map that uses chaining with linked lists to handle collisions.
 * This custom hash map is specifically designed to store and manage 'Patient' objects.
 */
public class HashMap {
    private static final int DEFAULT_CAPACITY = 16; // Default initial capacity
    private LinkedList<Patient>[] table; // Using built-in LinkedList for chaining
    private int capacity;
    private int size = 0;


    /**
     * This method constructs a new HashMap with a specified capacity.
     * Initializes all buckets (linked lists) within the hash table.
     *
     * @param capacity the initial capacity of the hash map
     */
    public HashMap(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * This constructs a new HashMap with the default capacity.
     */
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * This method generates the hash code for a given key (Patient) using its hashCode method, ensuring it fits within the table's bounds.
     *
     * @param key the Patient object whose hash code needs to be calculated
     * @return the index in the hash table where the key should be stored
     */
    private int hash(Patient key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    /**
     * This method adds a Patient to the hash map. If the Patient is already present, it replaces the old entry with the new one.
     *
     * @param key the Patient to be added or updated in the hash map
     */
    public void put(Patient key) {
        int index = hash(key);
        LinkedList<Patient> list = table[index];

        // Check for duplicates
        for (Patient p : list) {
            if (p.equals(key)) {
                list.remove(p);
                list.add(key); // Replace existing entry with new one
                return;
            }
        }

        list.add(key);
        size++;
    }

    /**
     * This method checks if a specific Patient is stored in the hash map.
     *
     * @param key the Patient to check for existence in the hash map
     * @return true if the Patient exists, false otherwise
     */
    public boolean contains(Patient key) {
        int index = hash(key);
        LinkedList<Patient> list = table[index];
        return list.contains(key);
    }

    /**
     * This method removes a specific Patient from the hash map if it exists.
     *
     * @param key the Patient to be removed
     * @return true if the Patient was removed successfully, false otherwise
     */
    public boolean remove(Patient key) {
        int index = hash(key);
        LinkedList<Patient> list = table[index];
        boolean wasRemoved = list.remove(key);
        if (wasRemoved) {
            size--;
        }
        return wasRemoved;
    }


    /**
     * This method returns the number of Patients currently stored in the hash map.
     *
     * @return the size of the hash map
     */
    public int size() {
        return size;
    }

    /**
     * This method prints a visual representation of the hash map to the standard output.
     * Displays each bucket's contents followed by 'null' to indicate the end of the linked list.
     */
    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.print("Bucket " + i + ": ");
                for (Patient p : table[i]) {
                    System.out.print(p + " -> ");
                }
                System.out.println("null");
            }
        }
    }
}
