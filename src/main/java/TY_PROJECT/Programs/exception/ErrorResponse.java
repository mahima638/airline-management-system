package TY_PROJECT.Programs.exception;
import java.time.LocalDate;

public class ErrorResponse {
	    private int status;
	    private String message;
	    private LocalDate timestamp;

	    public ErrorResponse(int status, String message) {
	        this.status = status;
	        this.message = message;
	        this.timestamp = LocalDate.now();
	    }

	    public int getStatus() { return status; }
	    public String getMessage() { return message; }
	    public LocalDate getTimestamp() { return timestamp; }
	}


