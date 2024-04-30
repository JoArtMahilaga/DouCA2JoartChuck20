import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * Appointments are comparable based on triage level and date.
 */
public class Appointment implements Comparable<Appointment>, Serializable {
    private String patientFirstName;
    private String patientLastName;
    private LocalDate patientDOB;
    private String issue;
    private LocalDate date;
    private int triageLevel;
    private String doctorFullName;

    /**
     * Constructs a new Appointment with the specified details.
     *
     * @param patientFirstName the first name of the patient
     * @param patientLastName the last name of the patient
     * @param patientDOB the date of birth of the patient
     * @param issue the medical issue described for the appointment
     * @param date the date of the appointment
     * @param triageLevel the urgency level of the appointment
     * @param doctorFullName the full name of the doctor assigned to the appointment
     */
    public Appointment(String patientFirstName, String patientLastName, LocalDate patientDOB, String issue, LocalDate date, int triageLevel, String doctorFullName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientDOB = patientDOB;
        this.issue = issue;
        this.date = date;
        this.triageLevel = triageLevel;
        this.doctorFullName = doctorFullName;
    }

    // Getters and setters

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(LocalDate patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(int triageLevel) {
        this.triageLevel = triageLevel;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    @Override
    public int compareTo(Appointment other) {
        // Compare appointments based on triage level and then by date
        int levelCompare = Integer.compare(this.triageLevel, other.triageLevel);
        if (levelCompare != 0) {
            return levelCompare * -1; // Higher priority triage level first
        }
        return this.date.compareTo(other.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return triageLevel == that.triageLevel &&
                Objects.equals(patientFirstName, that.patientFirstName) &&
                Objects.equals(patientLastName, that.patientLastName) &&
                Objects.equals(patientDOB, that.patientDOB) &&
                Objects.equals(issue, that.issue) &&
                Objects.equals(date, that.date) &&
                Objects.equals(doctorFullName, that.doctorFullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientFirstName, patientLastName, patientDOB, issue, date, triageLevel, doctorFullName);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientDOB=" + patientDOB +
                ", issue='" + issue + '\'' +
                ", date=" + date +
                ", triageLevel=" + triageLevel +
                ", doctorFullName='" + doctorFullName + '\'' +
                '}';
    }
}
