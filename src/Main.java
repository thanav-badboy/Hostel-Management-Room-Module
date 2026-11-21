import java.util.List;
import java.util.Scanner;

/**
 * Main Application Class: Main
 * 
 * College Hostel Management System - Room Management Module (Member 2)
 * 
 * Features:
 * 1. Add Room (Single, Double, Triple)
 * 2. View Rooms (All, Available, Occupied - sorted via manual Merge Sort)
 * 3. Check Room Availability (Search via Room ID or manual Binary Search on Room Number)
 * 4. Allocate Room to Student (Validates capacity, prevents duplicates)
 * 5. Vacate Room (Removes student, frees bed)
 * 6. Room Allocation Details (Room-wise student occupancy & history)
 * 7. Member 1 Integration Preview (Mock Student Roster)
 * 8. Exit
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RoomService roomService = new RoomManager();

    public static void main(String[] args) {
        // Pre-populate sample rooms for immediate testing & viva demonstration
        seedInitialRooms();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput("Enter your choice (1-8): ");

            System.out.println();
            switch (choice) {
                case 1:
                    handleAddRoom();
                    break;
                case 2:
                    handleViewRooms();
                    break;
                case 3:
                    handleCheckAvailability();
                    break;
                case 4:
                    handleAllocateRoom();
                    break;
                case 5:
                    handleVacateRoom();
                    break;
                case 6:
                    handleAllocationDetails();
                    break;
                case 7:
                    handleMember1Integration();
                    break;
                case 8:
                    System.out.println("Thank you for using the College Hostel Room Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection! Please enter a number between 1 and 8.");
            }
            if (running) {
                System.out.println("\nPress Enter to return to the main menu...");
                scanner.nextLine();
            }
        }
    }

    /**
     * Seeds initial test rooms into the system.
     */
    private static void seedInitialRooms() {
        roomService.addRoom(new SingleRoom("R101", 101));
        roomService.addRoom(new DoubleRoom("R102", 102));
        roomService.addRoom(new TripleRoom("R103", 103));
        roomService.addRoom(new SingleRoom("R201", 201));
        roomService.addRoom(new DoubleRoom("R202", 202));
        roomService.addRoom(new TripleRoom("R203", 203));

        // Pre-allocate a couple of students for demonstration
        try {
            roomService.allocateRoom("R101", "STU101");
            roomService.allocateRoom("R102", "STU102");
        } catch (Exception e) {
            // Seed allocation ignore
        }
    }

    // ==================== MENU DISPLAY ====================

    private static void printMainMenu() {
        System.out.println("\n==================================================================");
        System.out.println("         COLLEGE HOSTEL MANAGEMENT - ROOM MODULE (MEMBER 2)       ");
        System.out.println("==================================================================");
        System.out.println("  1. Add Room (Single / Double / Triple)");
        System.out.println("  2. View Rooms (All / Available / Occupied - Sorted via MergeSort)");
        System.out.println("  3. Check Room Availability (Search by ID or Binary Search)");
        System.out.println("  4. Allocate Room to Student (Capacity & Duplicate Check)");
        System.out.println("  5. Vacate Room (Free Student Allocation)");
        System.out.println("  6. View Room Allocation Details (Student Occupancy)");
        System.out.println("  7. View Member 1 Integration (Mock Student Roster)");
        System.out.println("  8. Exit Application");
        System.out.println("==================================================================");
    }

    // ==================== FEATURE 1: ADD ROOM ====================

    private static void handleAddRoom() {
        System.out.println("--- [1] ADD NEW ROOM ---");
        String roomId = readStringInput("Enter Room ID (e.g. R301): ").toUpperCase();
        int roomNumber = readIntInput("Enter Room Number (e.g. 301): ");

        System.out.println("Select Room Type:");
        System.out.println("  1. Single Room (Capacity: 1, Rent: ₹7,500)");
        System.out.println("  2. Double Room (Capacity: 2, Rent: ₹4,500)");
        System.out.println("  3. Triple Room (Capacity: 3, Rent: ₹3,000)");
        int typeChoice = readIntInput("Select Type (1-3): ");

        Room newRoom;
        switch (typeChoice) {
            case 1:
                newRoom = new SingleRoom(roomId, roomNumber);
                break;
            case 2:
                newRoom = new DoubleRoom(roomId, roomNumber);
                break;
            case 3:
                newRoom = new TripleRoom(roomId, roomNumber);
                break;
            default:
                System.out.println("Error: Invalid room type selected. Room creation cancelled.");
                return;
        }

        try {
            roomService.addRoom(newRoom);
            System.out.println("Success: " + newRoom.getRoomType() + " Room " + roomId 
                    + " (No. " + roomNumber + ") added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    // ==================== FEATURE 2: VIEW ROOMS ====================

    private static void handleViewRooms() {
        System.out.println("--- [2] VIEW ROOMS ---");
        System.out.println("  1. View All Rooms (Sorted by Room Number)");
        System.out.println("  2. View Available Rooms Only");
        System.out.println("  3. View Occupied Rooms Only");
        int subChoice = readIntInput("Select Option (1-3): ");

        List<Room> roomsToDisplay;
        String header;

        switch (subChoice) {
            case 1:
                roomsToDisplay = roomService.getAllRooms();
                header = "ALL ROOMS (Sorted via manual Merge Sort)";
                break;
            case 2:
                roomsToDisplay = roomService.getAvailableRooms();
                header = "AVAILABLE ROOMS (Has Remaining Bed Capacity)";
                break;
            case 3:
                roomsToDisplay = roomService.getOccupiedRooms();
                header = "OCCUPIED ROOMS (Full Capacity Reached)";
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        System.out.println("\n--- " + header + " ---");
        displayRoomTable(roomsToDisplay);
    }

    private static void displayRoomTable(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found matching the criteria.");
            return;
        }

        System.out.printf("%-10s | %-8s | %-8s | %-10s | %-10s | %-10s | %-12s | %-12s%n",
                "Room ID", "Number", "Type", "Capacity", "Occupancy", "Available", "Status", "Rent/Student");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Room r : rooms) {
            System.out.printf("%-10s | %-8d | %-8s | %-10d | %-10d | %-10d | %-12s | ₹%-11.2f%n",
                    r.getRoomId(),
                    r.getRoomNumber(),
                    r.getRoomType(),
                    r.getCapacity(),
                    r.getOccupancy(),
                    r.getAvailableCapacity(),
                    r.getStatus().getDisplayName(),
                    r.getMonthlyRent());
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Total Rooms Listed: " + rooms.size());
    }

    // ==================== FEATURE 3: CHECK ROOM AVAILABILITY ====================

    private static void handleCheckAvailability() {
        System.out.println("--- [3] CHECK ROOM AVAILABILITY & SEARCH ---");
        System.out.println("  1. Search by Room ID (O(1) HashMap Lookup)");
        System.out.println("  2. Search by Room Number (O(log n) Manual Binary Search)");
        int searchOption = readIntInput("Select Search Method (1-2): ");

        try {
            Room foundRoom = null;
            if (searchOption == 1) {
                String id = readStringInput("Enter Room ID to search (e.g. R101): ");
                foundRoom = roomService.searchByRoomId(id);
                System.out.println("\n[Search Result via HashMap O(1)]");
            } else if (searchOption == 2) {
                int number = readIntInput("Enter Room Number to search (e.g. 102): ");
                foundRoom = roomService.searchByRoomNumber(number);
                System.out.println("\n[Search Result via Manual Binary Search O(log n)]");
            } else {
                System.out.println("Invalid search method selected.");
                return;
            }

            // Demonstrate Polymorphism by invoking subclass overridden method
            foundRoom.displayRoomDetails();

        } catch (RoomNotFoundException e) {
            System.out.println("RoomNotFoundException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // ==================== FEATURE 4: ALLOCATE ROOM ====================

    private static void handleAllocateRoom() {
        System.out.println("--- [4] ALLOCATE ROOM TO STUDENT ---");
        String roomId = readStringInput("Enter Target Room ID (e.g. R102): ").toUpperCase();
        String studentId = readStringInput("Enter Student ID (e.g. STU103): ").toUpperCase();

        // Validate student ID format / roster
        if (!StudentIntegrationHelper.isValidStudentId(studentId)) {
            System.out.println("Warning: Invalid student ID format. Must match STU### (e.g., STU101).");
            return;
        }

        try {
            boolean success = roomService.allocateRoom(roomId, studentId);
            if (success) {
                System.out.println("\nSUCCESS: Student " + studentId + " (" 
                        + StudentIntegrationHelper.getStudentDisplayName(studentId) 
                        + ") has been allocated to Room " + roomId + "!");
                
                // Show updated room details
                Room room = roomService.searchByRoomId(roomId);
                System.out.println("Updated Room Status: " + room.getStatus().getDisplayName() 
                        + " | Occupancy: " + room.getOccupancy() + "/" + room.getCapacity());
            }
        } catch (RoomNotFoundException e) {
            System.out.println("Allocation Failed: " + e.getMessage());
        } catch (RoomFullException e) {
            System.out.println("Allocation Failed: " + e.getMessage());
        } catch (DuplicateAllocationException e) {
            System.out.println("Allocation Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    // ==================== FEATURE 5: VACATE ROOM ====================

    private static void handleVacateRoom() {
        System.out.println("--- [5] VACATE ROOM ---");
        String roomId = readStringInput("Enter Room ID to vacate from (e.g. R101): ").toUpperCase();
        String studentId = readStringInput("Enter Student ID to vacate (e.g. STU101): ").toUpperCase();

        try {
            boolean success = roomService.vacateRoom(roomId, studentId);
            if (success) {
                System.out.println("\nSUCCESS: Student " + studentId + " vacated from Room " + roomId + ".");
                Room room = roomService.searchByRoomId(roomId);
                System.out.println("Updated Room Status: " + room.getStatus().getDisplayName() 
                        + " | Available Capacity: " + room.getAvailableCapacity() + " bed(s)");
            }
        } catch (RoomNotFoundException e) {
            System.out.println("Vacate Failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Vacate Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    // ==================== FEATURE 6: ALLOCATION DETAILS ====================

    private static void handleAllocationDetails() {
        System.out.println("--- [6] ROOM ALLOCATION DETAILS & OCCUPANCY ---");
        List<Room> allRooms = roomService.getAllRooms();

        System.out.println("\n----------------- CURRENT ROOM ALLOCATIONS -----------------");
        for (Room room : allRooms) {
            System.out.println("Room: " + room.getRoomId() + " (No: " + room.getRoomNumber() 
                    + ", Type: " + room.getRoomType() + ") | Status: " + room.getStatus().getDisplayName());
            List<String> students = room.getAllocatedStudents();
            if (students.isEmpty()) {
                System.out.println("  └── [No students allocated - Room Empty]");
            } else {
                for (String stuId : students) {
                    System.out.println("  └── " + stuId + " -> " + StudentIntegrationHelper.getStudentDisplayName(stuId));
                }
            }
            System.out.println();
        }

        System.out.println("----------------- ALLOCATION AUDIT LOGS ------------------");
        List<RoomAllocation> history = roomService.getAllAllocations();
        if (history.isEmpty()) {
            System.out.println("No allocation history records available.");
        } else {
            for (RoomAllocation allocation : history) {
                System.out.println(allocation);
            }
        }
    }

    // ==================== FEATURE 7: MEMBER 1 INTEGRATION ====================

    private static void handleMember1Integration() {
        System.out.println("--- [7] MEMBER 1 (STUDENT MANAGEMENT) INTEGRATION ---");
        StudentIntegrationHelper.printAvailableTestStudents();
    }

    // ==================== SAFE INPUT UTILITIES ====================

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer! Please enter numeric digits only.");
            }
        }
    }

    private static String readStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please enter a value.");
        }
    }
}
