import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract Base Class: Room
 * 
 * Demonstrates:
 * 1. Abstraction: Defines the abstract template and common behavior for all hostel rooms.
 * 2. Encapsulation: All state variables are private and accessed via controlled getters/setters.
 */
public abstract class Room {
    private String roomId;
    private int roomNumber;
    private int capacity;
    private RoomStatus status;
    private List<String> allocatedStudents;

    /**
     * Parameterized Constructor
     * @param roomId Unique room code (e.g., "R-101")
     * @param roomNumber Physical room number (e.g., 101)
     * @param capacity Total student capacity of the room
     */
    public Room(String roomId, int roomNumber, int capacity) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be null or empty.");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Room capacity must be greater than zero.");
        }

        this.roomId = roomId.trim().toUpperCase();
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.AVAILABLE;
        this.allocatedStudents = new ArrayList<>();
    }

    // ==================== ABSTRACT METHODS (ABSTRACTION) ====================

    /**
     * Returns the specific room type (e.g., Single, Double, Triple).
     * Must be implemented by concrete subclasses.
     */
    public abstract String getRoomType();

    /**
     * Returns the base monthly rent for this room category.
     * Demonstrates dynamic polymorphism when invoked through a base Room reference.
     */
    public abstract double getMonthlyRent();

    /**
     * Displays formatted details specific to this room type.
     */
    public abstract void displayRoomDetails();

    // ==================== CONCRETE BUSINESS METHODS ====================

    /**
     * Checks if the room can accept more student allocations.
     */
    public boolean isAvailable() {
        return this.status != RoomStatus.UNDER_MAINTENANCE && allocatedStudents.size() < capacity;
    }

    /**
     * Returns the current number of allocated students.
     */
    public int getOccupancy() {
        return allocatedStudents.size();
    }

    /**
     * Returns the remaining capacity in the room.
     */
    public int getAvailableCapacity() {
        return Math.max(0, capacity - allocatedStudents.size());
    }

    /**
     * Allocates a student to this room if capacity permits.
     * @param studentId Unique identifier of the student
     * @return true if allocation succeeded
     * @throws RoomFullException if room is already at full capacity
     */
    public boolean allocateStudent(String studentId) throws RoomFullException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (!isAvailable()) {
            throw new RoomFullException("Room " + roomId + " (No. " + roomNumber + ") is already full! Current: " 
                    + getOccupancy() + "/" + capacity);
        }

        allocatedStudents.add(studentId.trim());
        syncStatus();
        return true;
    }

    /**
     * Removes a student allocation from this room.
     * @param studentId Unique identifier of the student to remove
     * @return true if student was present and removed
     */
    public boolean vacateStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        boolean removed = allocatedStudents.remove(studentId.trim());
        if (removed) {
            syncStatus();
        }
        return removed;
    }

    /**
     * Internal synchronization of room status based on current occupancy.
     */
    private void syncStatus() {
        if (this.status == RoomStatus.UNDER_MAINTENANCE) {
            return;
        }
        if (allocatedStudents.size() >= capacity) {
            this.status = RoomStatus.OCCUPIED;
        } else {
            this.status = RoomStatus.AVAILABLE;
        }
    }

    // ==================== GETTERS & SETTERS (ENCAPSULATION) ====================

    public String getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    public List<String> getAllocatedStudents() {
        return Collections.unmodifiableList(allocatedStudents);
    }

    @Override
    public String toString() {
        return String.format("[%s] No: %d | Type: %-6s | Cap: %d | Occ: %d | Avail: %d | Status: %s | Rent: ₹%.2f",
                roomId, roomNumber, getRoomType(), capacity, getOccupancy(), getAvailableCapacity(), status, getMonthlyRent());
    }
}
