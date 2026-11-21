/**
 * Custom Exception thrown when a requested room cannot be found
 * by its Room ID or Room Number.
 * 
 * Demonstrates: Custom Exception Handling in Java.
 */
public class RoomNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
