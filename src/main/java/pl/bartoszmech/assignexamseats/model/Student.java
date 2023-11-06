package pl.bartoszmech.assignexamseats.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nickname;
    private String schoolClass;

    public Student(String nickname, String schoolClass) {
        this.nickname = nickname;
        this.schoolClass = schoolClass;
    }

    public Student() {}

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchoolClass() {
        return schoolClass;
    }
}
