import java.time.LocalDate;




    public class Appointment implements Comparable<Appointment> {
            private String firstName;
            private String lastName;
            private LocalDate dob;
            private String issue;
            private LocalDate date;
            private int triageLevel;
            private String doctorFullName;

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Appointment that = (Appointment) obj;
                return triageLevel == that.triageLevel &&
                        firstName.equals(that.firstName) &&
                        lastName.equals(that.lastName) &&
                        dob.equals(that.dob) &&
                        issue.equals(that.issue) &&
                        date.equals(that.date) &&
                        doctorFullName.equals(that.doctorFullName);
            }


            @Override
            public int compareTo(Appointment other) {
                if (this.triageLevel != other.triageLevel) {
                    return this.triageLevel - other.triageLevel;
                } else {
                    return this.date.compareTo(other.date);
                }
            }
        }
