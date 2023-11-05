package pl.bartoszmech.assignexamseats.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(name = "horizontal_seats")
    private Integer columns;

    @Column(name = "vertical_seats")
    private Integer rows;

    public Classroom(String name, Integer columns, Integer rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }

    public Classroom() {}
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
