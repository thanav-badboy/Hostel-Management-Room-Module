import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Model Class: RoomAllocation
 * 
 * Encapsulates the record of a student assigned to a hostel room.
 * Demonstrates: Encapsulation and Data Transfer Object (DTO) pattern.
 */
public class RoomAllocation {
    private static int counter = 1000;

    private String allocationId;
    private String roomId;
    private String studentId;
    private String allocatedAt;
    private boolean active;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RoomAllocation(String roomId, String studentId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be empty.");
        }
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }

        this.allocationId = "AL-" + (++counter);
        this.roomId = roomId.trim().toUpperCase();
        this.studentId = studentId.trim().toUpperCase();
        this.allocatedAt = LocalDateTime.now().format(FORMATTER);
        this.active = true;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAllocatedAt() {
        return allocatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("[%s] Student: %-10s -> Room: %-6s | Date: %s | Status: %s",
                allocationId, studentId, roomId, allocatedAt, (active ? "ACTIVE" : "VACATED"));
    }
}
