/**
 * Concrete Subclass: TripleRoom
 * 
 * Demonstrates:
 * 1. Inheritance: Extends abstract class Room.
 * 2. Polymorphism: Overrides getRoomType(), getMonthlyRent(), and displayRoomDetails().
 */
public class TripleRoom extends Room {
    private static final int TRIPLE_CAPACITY = 3;
    private static final double MONTHLY_RENT = 3000.00;

    public TripleRoom(String roomId, int roomNumber) {
        super(roomId, roomNumber, TRIPLE_CAPACITY);
    }

    @Override
    public String getRoomType() {
        return "Triple";
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
        System.out.println("  Category      : Triple Sharing (3 Students)");
        System.out.println("  Capacity      : " + getCapacity() + " Students");
        System.out.println("  Occupancy     : " + getOccupancy() + "/" + getCapacity());
        System.out.println("  Available Beds: " + getAvailableCapacity());
        System.out.println("  Status        : " + getStatus().getDisplayName());
        System.out.println("  Monthly Rent  : ₹" + String.format("%.2f", getMonthlyRent()) + " per student");
        System.out.println("  Students      : " + (getAllocatedStudents().isEmpty() ? "None" : getAllocatedStudents()));
        System.out.println("--------------------------------------------------");
    }
}
