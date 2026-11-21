/**
 * Represents the operational status of a hostel room.
 * 
 * Enums ensure type safety and prevent invalid status values.
 */
public enum RoomStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    UNDER_MAINTENANCE("Under Maintenance");

    private final String displayName;

    RoomStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
