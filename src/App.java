import java.io.*;
import java.util.Scanner;
import java.util.Random;

import java.util.ArrayList;


/**
 *This app A console application to manage doctor queues and patient data, including saving and loading from a file.
 */

public class App {
    private static ArrayList<DoctorQueue> doctorQueues = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        loadDataFromFile();  //Loads data from file at start
        setupDoctorsAndQueues();
        runMainMenu();
        saveDataToFile();  //Saves data to file before exiting
        scanner.close();
    }


    /**
     *This is the main entry point of the application which initializes system and manages workflow from startup to exit.
     *
     */
    private static void saveDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            out.writeObject(patients);
            out.writeObject(doctorQueues);
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    /**
     *This method loads patients and doctor queues from a binary file if it exists.
     */
    private static void loadDataFromFile() {
        File dataFile = new File("data.bin");
        if (dataFile.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile))) {
                patients = (ArrayList<Patient>) in.readObject();
                doctorQueues = (ArrayList<DoctorQueue>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to load data: " + e.getMessage());
            }
        }
    }

    /**
     *This sets up the doctor queues based on user input, including queue capacities and the number of doctors.
     */
    private static void setupDoctorsAndQueues() {
        System.out.println("Enter the upper bound for the queues:");
        int capacity = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of doctors rostered today:");
        int numDoctors = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the names of the doctors rostered:");
        for (int i = 0; i < numDoctors; i++) {
            String doctorName = scanner.nextLine().trim();
                doctorQueues.add(new DoctorQueue(capacity, doctorName));
        }
    }

    /**
     * displays the main menu, allows the user to choose a variety of options such as adding patients, deleting patients,
     * creating appointments, display all patients and appointments, and exiting the appication.
     */
    private static void runMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new patient");
            System.out.println("2. Delete a patient");
            System.out.println("3. Display all patients");
            System.out.println("4. Create a new appointment");
            System.out.println("5. Display all appointments");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    deletePatient();
                    break;
                case 3:
                    displayAllPatients();
                    break;
                case 4:
                    createNewAppointment();
                    break;
                case 5:
                    displayAllAppointments();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 6.");
            }
        }
    }

    /**
     * adds a new patient to the system.
     */
    private static void addNewPatient() {
        System.out.println("Enter patient's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter patient's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter patient's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        Patient newPatient = new Patient(firstName, lastName, dob);
        if (patients.contains(newPatient)) {
            System.out.println("A patient with this name and date of birth already exists.");
            return;
        }
        patients.add(newPatient);
        System.out.println("Patient added successfully.");
    }

    /**
     * removes a patient from the system.
     */
    private static void deletePatient() {
        System.out.println("Enter patient's first name to delete:");
        String firstName = scanner.nextLine();
        System.out.println("Enter patient's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter patient's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        Patient patientToRemove = new Patient(firstName, lastName, dob);
        if (patients.remove(patientToRemove)) {
            System.out.println("Patient deleted successfully.");
            doctorQueues.forEach(queue -> queue.removePatient(patientToRemove));
        } else {
            System.out.println("No such patient found.");
        }
    }

    /**
     * displays registered patients in the system.
     */
    private static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            System.out.println("Patients registered:");
            patients.forEach(System.out::println);
        }
    }

    /**
     * creates a new appointment for a patient.
     */
    private static void createNewAppointment() {
        System.out.println("Enter the doctor's name for the appointment:");
        String doctorName = scanner.nextLine();
        System.out.println("Enter the patient's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the patient's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter the patient's date of birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();

        Patient patient = new Patient(firstName, lastName, dob);
        if (!patients.contains(patient)) {
            System.out.println("No such patient registered. Please add the patient first.");
            return;
        }

        int triageLevel = 1 + random.nextInt(5); // Random triage level between 1 and 5
        Appointment appointment = new Appointment(patient.toString(), doctorName, triageLevel);
        boolean appointmentAdded = doctorQueues.stream()
                .filter(queue -> queue.getDoctorName().equalsIgnoreCase(doctorName))
                .findFirst()
                .map(queue -> queue.add(appointment))
                .orElse(false);

        if (appointmentAdded) {
            System.out.println("Appointment added successfully.");
        } else {
            System.out.println("Appointment could not be added. Check doctor's name and queue capacity.");
        }
    }

    /**
     * Displays all appointments scheduled in the system.
     */
    private static void displayAllAppointments() {
        System.out.println("All appointments:");
        doctorQueues.forEach(DoctorQueue::displayAppointments);
    }

    // Custom queue class
    static class DoctorQueue implements Serializable {
        private ArrayList<Appointment> appointments = new ArrayList<>();
        private int capacity;
        private String doctorName;

        /**
         * displays all appointments across all doctor queues.
         */
        DoctorQueue(int capacity, String doctorName) {
            this.capacity = capacity;
            this.doctorName = doctorName;
        }

        // class implementation
        /**
         * Adds an appointment to the doctor's queue if capacity allows.
         * @param appointment The appointment to be added
         * @return true if added successfully, false otherwise
         */
        boolean add(Appointment appointment) {
            if (appointments.size() < capacity && appointment.getDoctorFullName().equalsIgnoreCase(doctorName)) {
                appointments.add(appointment);
                return true;
            }
            return false;
        }

        /**
         * Removes all appointments associated with a specific patient.
         * @param patient The patient whose appointments are to be removed
         */
        void removePatient(Patient patient) {
            appointments.removeIf(app -> app.getPatientName().equals(patient.toString()));
        }

        /**
         * Displays all appointments for this doctor.
         */
        String getDoctorName() {
            return doctorName;
        }

        void displayAppointments() {
            if (appointments.isEmpty()) {
                System.out.println("Doctor: " + doctorName + " has no appointments.");
            } else {
                System.out.println("Doctor: " + doctorName);
                appointments.forEach(app -> System.out.println("  Patient: " + app.getPatientName() + " (Triage level: " + app.getTriageLevel() + ")"));
            }
        }
    }

    /**
     * Represents an appointment with a patient's details, doctor, and triage level.
     */
    // Appointment class
    static class Appointment {
        private String patientName;
        private String doctorFullName;
        private int triageLevel;

        Appointment(String patientName, String doctorFullName, int triageLevel) {
            this.patientName = patientName;
            this.doctorFullName = doctorFullName;
            this.triageLevel = triageLevel;
        }

        String getPatientName() {
            return patientName;
        }

        String getDoctorFullName() {
            return doctorFullName;
        }

        int getTriageLevel() {
            return triageLevel;
        }
    }

    /**
     * Represents a patient with a first name, last name, and date of birth.
     * Ensures uniqueness through these attributes.
     */
    // Patient class for managing patient data
    static class Patient {
        private String firstName;
        private String lastName;
        private String dob;

        Patient(String firstName, String lastName, String dob) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Patient patient = (Patient) obj;
            return firstName.equals(patient.firstName) &&
                    lastName.equals(patient.lastName) &&
                    dob.equals(patient.dob);
        }

        @Override
        public int hashCode() {
            return (firstName + lastName + dob).hashCode();
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + " (DOB: " + dob + ")";
        }
    }



}

