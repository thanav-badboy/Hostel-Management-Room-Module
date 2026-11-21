/**
 * Custom Exception thrown when an allocation attempt is made on a room
 * that has already reached its maximum capacity.
 * 
 * Demonstrates: Custom Exception Handling in Java.
 */
public class RoomFullException extends Exception {
    private static final long serialVersionUID = 1L;

    public RoomFullException(String message) {
        super(message);
    }

    public RoomFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
