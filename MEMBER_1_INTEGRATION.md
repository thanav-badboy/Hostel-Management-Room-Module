# Member 1 (Student Management) Integration Guide

**College Hostel Management System**  
**Module Contract between Member 1 (Student) & Member 2 (Room)**

---

## 1. Architectural Relationship
- **Member 1 Responsibility:** Manages Student records (Registration, Name, Contact, Course, Guardian info).
- **Member 2 Responsibility:** Manages Room inventory, allocations, and room occupancy.
- **Common Identifier:** `studentId` (e.g. `"STU101"`, `"STU102"`).

Member 2 does **not** duplicate student data. Member 1 provides the verified `studentId`, and Member 2 assigns the student to a room while ensuring capacity and duplicate constraints.

---

## 2. Public API Signatures for Member 1

Member 1 can interact with Member 2's `RoomService` using the following method signatures:

```java
// Initialize the room service
RoomService roomService = new RoomManager();

// 1. Allocate a student to a room
boolean isAllocated = roomService.allocateRoom(String roomId, String studentId)
    throws RoomNotFoundException, RoomFullException, DuplicateAllocationException;

// 2. Vacate a student when they check out or leave the hostel
boolean isVacated = roomService.vacateRoom(String roomId, String studentId)
    throws RoomNotFoundException;

// 3. Check if a student is already allocated to any room
boolean hasRoom = roomService.isStudentAllocated(String studentId);

// 4. Retrieve the room assigned to a student or get students in a room
List<String> studentIds = roomService.getStudentsInRoom(String roomId)
    throws RoomNotFoundException;
```

---

## 3. Exception Handling Contract
When Member 1 calls `allocateRoom(roomId, studentId)`:

| Exception | Condition | Suggested Member 1 Action |
|---|---|---|
| `RoomNotFoundException` | `roomId` does not exist | Display "Selected room was not found in the hostel." |
| `RoomFullException` | Room has 0 available beds | Display "Room is already full. Please pick another room." |
| `DuplicateAllocationException` | `studentId` already has a room | Display "Student already has an active room allocation." |
| `IllegalArgumentException` | Empty or null `roomId`/`studentId` | Re-prompt the user for valid inputs. |

---

## 4. Test Student IDs for Standalone Verification
During demo and testing, use these pre-registered IDs:
- `STU101` -> Rahul Sharma (CSE)
- `STU102` -> Priya Patel (ECE)
- `STU103` -> Amit Verma (IT)
- `STU104` -> Sneha Kulkarni (Mech)
- `STU105` -> Vikas Nair (Civil)
- Or any ID formatted as `STU###` (e.g., `STU205`).

---

## 5. Teammate Pull Request Instructions
To satisfy the team Git requirement:
1. Fork or clone Member 1's repository.
2. Create a feature branch: `git checkout -b feature/room-integration-contract`.
3. Add `RoomService.java` interface or this integration specification to their project.
4. Submit a Pull Request titled:  
   `feat(integration): add Room Management Module interface and integration contract`
5. Have Member 1 review and merge it into their `main` branch.
