/**
 * Custom Exception thrown when an attempt is made to allocate a room to a student
 * who already has an active room allocation in the hostel.
 * 
 * Demonstrates: Custom Exception Handling in Java & Business Logic Validation.
 */
public class DuplicateAllocationException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateAllocationException(String message) {
        super(message);
    }

    public DuplicateAllocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
