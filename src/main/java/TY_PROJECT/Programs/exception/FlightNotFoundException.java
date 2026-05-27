package TY_PROJECT.Programs.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(Long id) {
        super("Flight not found with ID: " + id);
    }
}