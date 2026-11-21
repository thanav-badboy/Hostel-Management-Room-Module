/**
 * Concrete Subclass: SingleRoom
 * 
 * Demonstrates:
 * 1. Inheritance: Extends abstract class Room.
 * 2. Polymorphism: Overrides getRoomType(), getMonthlyRent(), and displayRoomDetails().
 */
public class SingleRoom extends Room {
    private static final int SINGLE_CAPACITY = 1;
    private static final double MONTHLY_RENT = 7500.00;

    public SingleRoom(String roomId, int roomNumber) {
        super(roomId, roomNumber, SINGLE_CAPACITY);
    }

    @Override
    public String getRoomType() {
        return "Single";
    }

    @Override
    public double getMonthlyRent() {
        return MONTHLY_RENT;
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("  Room ID       : " + getRoomId());
        System.out.println("  Room Number   : " + getRoomNumber());
        System.out.println("  Category      : Single Bed (Private Room)");
        System.out.println("  Capacity      : " + getCapacity() + " Student");
        System.out.println("  Occupancy     : " + getOccupancy() + "/" + getCapacity());
        System.out.println("  Available Beds: " + getAvailableCapacity());
        System.out.println("  Status        : " + getStatus().getDisplayName());
        System.out.println("  Monthly Rent  : ₹" + String.format("%.2f", getMonthlyRent()));
        System.out.println("  Students      : " + (getAllocatedStudents().isEmpty() ? "None" : getAllocatedStudents()));
        System.out.println("--------------------------------------------------");
    }
}
