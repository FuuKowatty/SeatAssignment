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

    public Student(String nickname) {
        this.nickname = nickname;
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
}
