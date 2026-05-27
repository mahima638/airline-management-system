package TY_PROJECT.Programs.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import org.springframework.web.bind.MethodArgumentNotValidException;
@ControllerAdvice
public class GlobalExceptionHandler {
	

	
	
	

	    // Handle RuntimeException (booking not found, flight not found etc)
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.NOT_FOUND.value(),
	            ex.getMessage()
	        );
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	        String message = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> error.getDefaultMessage())
	            .collect(Collectors.joining(", "));
	        
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            message
	        );
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }
	 // Handle validation errors
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ErrorResponse> handleValidationException(ConstraintViolationException ex) {
	        String message = ex.getConstraintViolations()
	            .stream()
	            .map(cv -> cv.getMessage())
	            .collect(Collectors.joining(", "));
	        
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            message
	        );
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	    }

	    // Handle any other unexpected exception
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            "Something went wrong: " + ex.getMessage()
	        );
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	    }
	}

