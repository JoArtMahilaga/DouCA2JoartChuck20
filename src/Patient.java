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


    //Setters and getters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }
}
