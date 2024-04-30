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
}