# College Hostel Management System - Room Management Module (Member 2)

**Author:** Member 2  
**Role:** Lead Developer - Room Management Module  
**Course Project:** College Hostel Management System (Console-based Java Application)  
**Assigned Module:** Room Management Module ONLY  

---

## 📌 1. Project Overview & Scope
This project implements the independent **Room Management Module** for a 4-member distributed College Hostel Management System.

### Team Responsibilities:
- **Member 1:** Student Management (Student registration, profile, contact info)
- **Member 2 (This Repository):** Room Management (Room inventory, categories, allocations, capacity, DSA)
- **Member 3:** Fee Management
- **Member 4:** Complaint Management

> **Integration Anchor:** `studentId` is used as the common identifier between Member 1 and Member 2. No student records are duplicated in this module.

---

## 🚀 2. Implemented Features
1. **Add Room:**
   - Single Room (Capacity: 1, Rent: ₹7,500)
   - Double Room (Capacity: 2, Rent: ₹4,500)
   - Triple Room (Capacity: 3, Rent: ₹3,000)
   - Validation for duplicate Room IDs and duplicate Room Numbers.
2. **View Rooms:**
   - View All Rooms (Sorted by room number using **manual Merge Sort**).
   - View Available Rooms (Displays rooms with available beds).
   - View Occupied Rooms (Displays rooms that are completely full).
3. **Check Room Availability:**
   - Search by Room ID ($O(1)$ HashMap lookup).
   - Search by Room Number ($O(\log n)$ **manual Binary Search**).
   - Displays occupancy, remaining capacity, status, and rent.
4. **Allocate Room:**
   - Assigns student to room using `studentId`.
   - Prevents duplicate allocation across the entire hostel ($O(1)$ HashSet check).
   - Prevents allocation beyond room capacity.
   - Automatically synchronizes room status to `OCCUPIED` when full.
5. **Vacate Room:**
   - Removes a student from a room.
   - Automatically restores room availability to `AVAILABLE`.
6. **Room Allocation Details:**
   - Lists all rooms with student occupants.
   - Comprehensive allocation audit log.
7. **Member 1 Integration Helper:**
   - Mock student roster with IDs (`STU101` through `STU105`) for live demonstration.

---

## ☕ 3. Technical Requirements & Concepts Demonstrated

### A. Object-Oriented Programming (OOP)
1. **Abstraction:**
   - Abstract class `Room` defines the abstract template (`getRoomType()`, `getMonthlyRent()`, `displayRoomDetails()`) while hiding internal status synchronization.
   - Interface `RoomService` defines pure business contracts decoupled from implementation.
2. **Inheritance:**
   - `SingleRoom extends Room`
   - `DoubleRoom extends Room`
   - `TripleRoom extends Room`
3. **Polymorphism:**
   - **Runtime Polymorphism (Method Overriding):** Subclasses override `displayRoomDetails()`, `getRoomType()`, and `getMonthlyRent()`. In `Main.java`, calling `room.displayRoomDetails()` dynamically executes the subclass behavior.
   - **Compile-time Polymorphism (Method Overloading):** Search capability overloaded by `searchByRoomId(String)` and `searchByRoomNumber(int)`.
4. **Encapsulation:**
   - All state fields (`roomId`, `roomNumber`, `capacity`, `status`, `allocatedStudents`) are `private`.
   - Accessed exclusively through getters/setters with strict boundary validation.

### B. Data Structures & Algorithms (DSA - Implemented Manually)
Located in `src/RoomSortAndSearch.java` (implemented completely from scratch):
1. **Merge Sort Algorithm:**
   - **Implementation:** Manual divide-and-conquer recursion and merge helper.
   - **Time Complexity:** $O(n \log n)$ best, average, and worst case.
   - **Usage:** Sorts rooms by `roomNumber` in Feature 2 (View Rooms) and prepares data for Binary Search.
2. **Binary Search Algorithm:**
   - **Implementation:** Manual iterative binary search on sorted rooms.
   - **Time Complexity:** $O(\log n)$.
   - **Usage:** Feature 3 (Search Room Availability by Room Number).

### C. Java Collections Framework
- **`ArrayList<Room>`:** Maintains dynamic, resizable ordered lists of rooms and allocation audit records.
- **`HashMap<String, Room>`:** Maps `roomId -> Room` for instantaneous $O(1)$ lookup.
- **`HashSet<String>`:** Stores all active allocated `studentId`s to enforce $O(1)$ duplicate allocation checks.

### D. Custom Exceptions
1. **`RoomNotFoundException`:** Thrown when a room ID or number does not exist.
2. **`RoomFullException`:** Thrown when an allocation exceeds room capacity.
3. **`DuplicateAllocationException`:** Thrown when a student attempts to occupy multiple rooms.

---

## 📂 4. File Structure
```
Hostel-Management-Room-Module/
├── .gitignore
├── README.md
├── MEMBER_1_INTEGRATION.md
└── src/
    ├── Room.java                     # Abstract base model (Abstraction & Encapsulation)
    ├── SingleRoom.java               # Concrete subclass (Inheritance & Polymorphism)
    ├── DoubleRoom.java               # Concrete subclass (Inheritance & Polymorphism)
    ├── TripleRoom.java               # Concrete subclass (Inheritance & Polymorphism)
    ├── RoomStatus.java               # Enum for room state (AVAILABLE, OCCUPIED, etc.)
    ├── RoomAllocation.java           # DTO model for allocation records
    ├── RoomSortAndSearch.java        # Manual DSA: Merge Sort & Binary Search
    ├── RoomNotFoundException.java    # Custom Exception
    ├── RoomFullException.java        # Custom Exception
    ├── DuplicateAllocationException.java # Custom Exception
    ├── RoomService.java              # Interface defining module operations
    ├── RoomManager.java              # Implementation using Collections & DSA
    ├── StudentIntegrationHelper.java # Member 1 integration adapter & mock data
    └── Main.java                     # Interactive console application (Scanner)
```

---

## 💻 5. Compilation & Execution Commands

### To Compile:
```powershell
javac src/*.java
```

### To Run:
```powershell
java -cp src Main
```

---

## 🎯 6. Quick Viva / Demonstration Walkthrough
When demonstrating to your faculty or examiner:
1. **Option 2 -> 1:** View All Rooms (Show that rooms are neatly sorted by Room Number via manual **Merge Sort**).
2. **Option 3 -> 2:** Search Room Number `102` (Explain manual **Binary Search** running in $O(\log n)$ time).
3. **Option 4:** Allocate `STU103` into room `R102`. Show capacity decrease.
4. **Option 4 (Duplicate Check):** Try to allocate `STU103` again into another room. Show custom **`DuplicateAllocationException`**.
5. **Option 4 (Full Capacity Check):** Allocate `STU104` to `R101` (Single Room, already full). Show custom **`RoomFullException`**.
6. **Option 5:** Vacate `STU101` from `R101`. Show that room status resets to `Available`.
7. **Option 6:** Show Allocation Details & Audit logs.
8. **Option 7:** Show Member 1 Integration contract.

---

## 🤝 7. Student & Room Module Integration Guide (Member 1 & Member 2)

This section establishes the official integration contract between **Member 1 (Student Management Module)** and **Member 2 (Room Management Module)** for the overall College Hostel Management System.

### A. Division of Module Responsibilities

| Responsibility Area | Student Management Module (Member 1) | Room Management Module (Member 2) |
|---|---|---|
| **Primary Domain** | Resident identity, contact, & academic profiles | Room inventory, bed capacities, & allocations |
| **Data Owned** | Student Name, Age, Gender, Phone, Email, Course | Room ID, Room Number, Capacity, Rent, Room Status |
| **Entity State** | Active, Updated, Removed from hostel records | Available, Occupied, Under Maintenance |
| **Module Boundary** | Does not manage room or bed numbers | Does not store or manage personal student details |

### B. Common Integration Anchor: `studentId`
- Both modules use `studentId` (e.g., `"STU101"`, `"STU102"`) as the universal foreign key / identifier.
- **Single Source of Truth:** Member 1 issues and validates genuine student IDs during registration. Member 2 stores only the `studentId` inside `allocatedStudents` in `Room.java` and `RoomAllocation.java`, ensuring zero data duplication across modules.

### C. Expected End-to-End Integration Workflow
```
[Student Registration]          [Room Allocation]               [Room De-allocation]
   (Member 1)                      (Member 2)                      (Member 2)
       │                               │                               │
       ▼                               ▼                               ▼
Student registers ──────> Validates studentId ────> Student checks out
with unique STU ID        via StudentService        from hostel room
                          & allocates bed (R101)    & frees bed capacity
```

1. **Step 1 (Registration):** Resident registers through Member 1's Student Module; a verified `studentId` (e.g., `STU101`) is issued.
2. **Step 2 (Pre-allocation Validation):** When a resident requests a room, Member 2's module references the `studentId`. Currently, during independent testing, Member 2 utilizes `StudentIntegrationHelper.isValidStudentId(studentId)`. In full system deployment, this hooks directly into Member 1's `StudentService.existsById(studentId)`.
3. **Step 3 (Room Allocation):** Member 2's `RoomManager.allocateRoom(roomId, studentId)` assigns the student, checks room capacity, prevents duplicate hostel allocation via `HashSet`, and marks the bed as occupied.
4. **Step 4 (Vacating / Checkout):** When a resident leaves, Member 2's `RoomManager.vacateRoom(roomId, studentId)` removes the `studentId` and restores room status to `AVAILABLE`.

### D. Important Validation Considerations
- **Non-Existent Student Prevention:** Room allocations must reject unregistered IDs before allocating beds.
- **Duplicate Allocation Guard:** Member 2's `DuplicateAllocationException` ensures one student cannot hold beds in multiple rooms simultaneously.
- **Capacity Overflow Guard:** Member 2's `RoomFullException` ensures room capacity limits (Single=1, Double=2, Triple=3) are strictly respected.
- **Referential Integrity on Student Deletion:** If Member 1 deletes a student record, Member 2's `vacateRoom` must be invoked to release the occupied bed.

### E. Unified System Collaboration
When both independent modules are combined into the unified Hostel Management System:
- Member 1's `StudentService` and Member 2's `RoomService` operate side-by-side.
- The shared console menu allows staff to register a student, view available rooms, and immediately allocate that student's ID to a selected room with end-to-end consistency.

