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
     *adds an appointment to the end of the queue if the appointment's doctor matches the queue's specified doctor
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
    }
}