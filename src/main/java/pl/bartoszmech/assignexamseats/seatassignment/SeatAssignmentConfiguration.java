package pl.bartoszmech.assignexamseats.seatassignment;

public class SeatAssignmentConfiguration {
    public SeatAssignmentFacade seatAssignmentFacadeForTest() {
        return new SeatAssignmentFacade(new SeatAssignmentValidator(), new SeatAssignmentGenerator());
    }
}
