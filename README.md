# College Hostel Management System - Room Management Module

**Author / Role:** Member 2 (Room Management Lead)  
**Project:** College Hostel Management System (Console-based Java Application)  
**Assigned Module:** Room Management Module ONLY  

---

## 📌 Module Overview
The **Room Management Module** manages hostel room inventory, room configurations (Single, Double, Triple), room availability tracking, and student room allocations/vacations.

### Team Roles & Modular Boundaries
- **Member 1:** Student Management (Maintains student records; integration via `studentId`)
- **Member 2 (Me):** Room Management (Room inventory, allocation, availability, DSA sorting & searching)
- **Member 3:** Fee Management
- **Member 4:** Complaint Management

---

## 🚀 Key Features
1. **Add Room:** Create rooms with Room ID, Room Number, Room Type (Single/Double/Triple), and Status.
2. **View Rooms:** List all rooms, available rooms, or occupied rooms with clean tabular formatting.
3. **Check Room Availability:** Search rooms by Room ID or Room Number, displaying current occupancy and remaining capacity.
4. **Allocate Room:** Assign a student to a room using `studentId`. Enforces capacity limits and prevents duplicate student allocation.
5. **Vacate Room:** Remove student allocation and automatically update room availability status.
6. **Room Allocation Details:** View comprehensive room-wise student occupancy.

---

## ☕ Technical Concepts Demonstrated
- **Object-Oriented Programming (OOP):**
  - **Abstraction:** Abstract base class `Room` and service interface `RoomService`.
  - **Inheritance:** Concrete subclasses `SingleRoom`, `DoubleRoom`, and `TripleRoom` extending `Room`.
  - **Polymorphism:** Runtime method overriding for category-specific rates & details; compile-time overloading for room search.
  - **Encapsulation:** Private data fields with getters, setters, and strict state validation.
- **Data Structures & Algorithms (DSA):**
  - **Manual Merge Sort:** Implemented from scratch to sort rooms by room number in $O(n \log n)$ time.
  - **Manual Binary Search:** Implemented from scratch to find room by room number in $O(\log n)$ time.
- **Java Collections Framework:**
  - `ArrayList`: Ordered room list and allocation history.
  - `HashMap`: Fast $O(1)$ lookup for rooms by `roomId`.
  - `HashSet`: Fast $O(1)$ duplicate allocation checks for `studentId`.
- **Custom Exceptions:**
  - `RoomNotFoundException`
  - `RoomFullException`
  - `DuplicateAllocationException`
