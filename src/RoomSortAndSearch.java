import java.util.ArrayList;
import java.util.List;

/**
 * Data Structures & Algorithms Utility: RoomSortAndSearch
 * 
 * Demonstrates manual implementation of classic DSA algorithms:
 * 1. Merge Sort Algorithm: Divide-and-Conquer sorting algorithm with O(n log n) time complexity.
 * 2. Binary Search Algorithm: Efficient search with O(log n) time complexity on sorted data.
 * 
 * NOTE: Both algorithms are implemented manually from scratch without using
 * java.util.Collections.sort() or java.util.Arrays.binarySearch().
 */
public class RoomSortAndSearch {

    // ==================== MANUAL MERGE SORT ALGORITHM ====================

    /**
     * Entry method to sort a list of rooms by Room Number in ascending order.
     * Time Complexity: O(n log n) in all cases (best, average, worst).
     * Space Complexity: O(n) for auxiliary merging arrays.
     *
     * @param rooms List of Room objects to sort.
     */
    public static void mergeSortByRoomNumber(List<Room> rooms) {
        if (rooms == null || rooms.size() <= 1) {
            return;
        }
        mergeSort(rooms, 0, rooms.size() - 1);
    }

    /**
     * Recursive Divide step of Merge Sort.
     * Divides the array into two halves until single-element sublists remain.
     */
    private static void mergeSort(List<Room> list, int left, int right) {
        if (left < right) {
            // Find mid point using overflow-safe calculation
            int mid = left + (right - left) / 2;

            // Conquer: recursively sort the first and second halves
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            // Combine: merge the sorted halves
            merge(list, left, mid, right);
        }
    }

    /**
     * Combine step of Merge Sort.
     * Merges two sorted sublists [left..mid] and [mid+1..right] into a single sorted sublist.
     */
    private static void merge(List<Room> list, int left, int mid, int right) {
        // Sizes of two sub-arrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary auxiliary lists
        List<Room> leftList = new ArrayList<>(n1);
        List<Room> rightList = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(mid + 1 + j));
        }

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getRoomNumber() <= rightList.get(j).getRoomNumber()) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftList, if any
        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightList, if any
        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    // ==================== MANUAL BINARY SEARCH ALGORITHM ====================

    /**
     * Searches for a Room by its roomNumber using manual Binary Search.
     * Requirement: The list MUST be sorted by roomNumber in ascending order beforehand.
     * Time Complexity: O(log n)
     * Space Complexity: O(1) iterative
     *
     * @param sortedRooms Pre-sorted list of rooms by room number.
     * @param targetRoomNumber Room number to find.
     * @return The Room matching the room number, or null if not found.
     */
    public static Room binarySearchByRoomNumber(List<Room> sortedRooms, int targetRoomNumber) {
        if (sortedRooms == null || sortedRooms.isEmpty()) {
            return null;
        }

        int low = 0;
        int high = sortedRooms.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Room midRoom = sortedRooms.get(mid);
            int midNumber = midRoom.getRoomNumber();

            if (midNumber == targetRoomNumber) {
                return midRoom; // Target found
            } else if (midNumber < targetRoomNumber) {
                low = mid + 1;  // Search in right half
            } else {
                high = mid - 1; // Search in left half
            }
        }

        return null; // Target not found
    }
}
