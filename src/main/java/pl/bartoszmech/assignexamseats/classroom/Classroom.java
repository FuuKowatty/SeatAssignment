package pl.bartoszmech.assignexamseats.classroom;

public class Classroom {
    private final Integer columns;
    private final Integer rows;

    public Classroom(Integer columns, Integer rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Integer getRows() {
        return rows;
    }
}
