import java.util.LinkedList;

/**
 * A simple implementation of a hash map that uses chaining with linked lists to handle collisions.
 * This custom hash map is specifically designed to store and manage 'Patient' objects.
 */
public class HashMap {
    private static final int DEFAULT_CAPACITY = 16; // Default initial capacity
    private LinkedList<Patient>[] table; // Using built-in LinkedList for chaining
    private int capacity;
    private int size = 0;


    /**
     * Constructs a new HashMap with a specified capacity.
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
     * Constructs a new HashMap with the default capacity.
     */
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Generates the hash code for a given key (Patient) using its hashCode method, ensuring it fits within the table's bounds.
     *
     * @param key the Patient object whose hash code needs to be calculated
     * @return the index in the hash table where the key should be stored
     */
    private int hash(Patient key) {
        return Math.abs(key.hashCode()) % capacity;


    }
}
