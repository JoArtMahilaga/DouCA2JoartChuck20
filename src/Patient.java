import java.time.LocalDate;
import java.util.Objects;

/**
 * This class contains all of the patients details and can set and list their appointments.
 */
public class Patient {
    private String firstName;
    private String secondName;
    private LocalDate dateOfBirth;
    private LocalDate dateJoined;


    public Patient() {

    }
    public Patient(String firstName, String secondName, LocalDate dateOfBirth, LocalDate dateJoined) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.dateJoined = dateJoined;
    }
}
