public class Enrollee implements Comparable<Enrollee> {
    private String userId;
    private String firstName;
    private String lastName;
    private int version;
    private String insuranceCompany;

    public Enrollee(String userId, String firstName, String lastName, int version, String insuranceCompany) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.insuranceCompany = insuranceCompany;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getVersion() {
        return version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    @Override
    public String toString() {
        return userId + "," + firstName + "," + lastName + "," + version + "," + insuranceCompany;
    }

    @Override
    public int compareTo(Enrollee enrollee) {
        int lastNameDiff = lastName.compareTo(enrollee.lastName);
        if (lastNameDiff == 0) {
            return firstName.compareTo(enrollee.firstName);
        }
        return lastNameDiff;

    }
}
