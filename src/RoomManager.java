import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service Implementation: RoomManager
 * 
 * Manages the hostel room inventory, allocation state, and queries.
 * Implements: RoomService
 * 
 * Demonstrates:
 * 1. Java Collections Framework:
 *    - ArrayList: Dynamic list to maintain ordered room entries.
 *    - HashMap: Key-value map for O(1) constant-time room lookup by Room ID.
 *    - HashSet: Set for O(1) duplicate allocation detection across students.
 * 2. Custom Exception Handling: Enforces business logic safety.
 * 3. DSA integration: Uses manual MergeSort and manual Binary Search from RoomSortAndSearch.
 */
public class RoomManager implements RoomService {

    // Dynamic list preserving insertion and custom sort order
    private final List<Room> roomList;

    // HashMap for O(1) instant lookup by Room ID
    private final Map<String, Room> roomMap;

    // HashSet for O(1) instantaneous duplicate student allocation check
    private final Set<String> activeAllocatedStudents;

    // History log of all allocations
    private final List<RoomAllocation> allocationHistory;

    /**
     * Default Constructor initializing collections.
     */
    public RoomManager() {
        this.roomList = new ArrayList<>();
        this.roomMap = new HashMap<>();
        this.activeAllocatedStudents = new HashSet<>();
        this.allocationHistory = new ArrayList<>();
    }

    // ==================== FEATURE 1: ADD ROOM ====================

    @Override
    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (roomMap.containsKey(room.getRoomId())) {
            throw new IllegalArgumentException("Room with ID " + room.getRoomId() + " already exists!");
        }

        // Check if room number is already taken
        for (Room existing : roomList) {
            if (existing.getRoomNumber() == room.getRoomNumber()) {
                throw new IllegalArgumentException("Room with Number " + room.getRoomNumber() + " already exists!");
            }
        }

        roomList.add(room);
        roomMap.put(room.getRoomId(), room);
    }

    // ==================== FEATURE 2: VIEW ROOMS ====================

    @Override
    public List<Room> getAllRooms() {
        // Return a copy sorted by room number using manual Merge Sort
        List<Room> sortedList = new ArrayList<>(roomList);
        RoomSortAndSearch.mergeSortByRoomNumber(sortedList);
        return sortedList;
    }

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> availableList = new ArrayList<>();
        for (Room room : roomList) {
            if (room.isAvailable()) {
                availableList.add(room);
            }
        }
        // Sort using manual Merge Sort
        RoomSortAndSearch.mergeSortByRoomNumber(availableList);
        return availableList;
    }

    @Override
    public List<Room> getOccupiedRooms() {
        List<Room> occupiedList = new ArrayList<>();
        for (Room room : roomList) {
            if (room.getStatus() == RoomStatus.OCCUPIED) {
                occupiedList.add(room);
            }
        }
        // Sort using manual Merge Sort
        RoomSortAndSearch.mergeSortByRoomNumber(occupiedList);
        return occupiedList;
    }

    @Override
    public Room searchByRoomId(String roomId) throws RoomNotFoundException {
        return null;
    }

    @Override
    public Room searchByRoomNumber(int roomNumber) throws RoomNotFoundException {
        return null;
    }

    @Override
    public boolean allocateRoom(String roomId, String studentId)
            throws RoomNotFoundException, RoomFullException, DuplicateAllocationException {
        return false;
    }

    @Override
    public boolean vacateRoom(String roomId, String studentId) throws RoomNotFoundException {
        return false;
    }

    @Override
    public List<RoomAllocation> getAllAllocations() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getStudentsInRoom(String roomId) throws RoomNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public boolean isStudentAllocated(String studentId) {
        return false;
    }
}
