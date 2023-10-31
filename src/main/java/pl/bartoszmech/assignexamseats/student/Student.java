package pl.bartoszmech.assignexamseats.student;

public class Student {
    private final String firstName;
    private final String lastName;
    private final Byte age;

    public Student(String firstName, String lastName, Byte age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Byte getAge() {
        return age;
    }
}
