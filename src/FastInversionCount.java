/**
 * This class provides a method to count the number of inversions in an array using the divide and conquer approach,
 * which is more efficient than the brute-force approach for larger datasets.
 */
public class FastInversionCount {

    /**
     * Counts the number of inversions in the given array by using a modified merge sort algorithm.
     * The inversion count is incremented during the merge step.
     *
     * @param array The array to count inversions in.
     * @return The number of inversions found in the array.
     */
    public int countInversions(int[] array) {
        // Create a temporary array to avoid modifying the original array during sorting.
        int[] tempArray = array.clone();
        // Begin the recursive merge sort process and count inversions.
        return mergeSortAndCount(array, tempArray, 0, array.length - 1);
    }

    /**
     * Recursively divides the array into halves, sorts and counts the inversions in each half,
     * and then merges the halves while counting split inversions.
     *
     * @param array     The array to sort and count inversions in.
     * @param tempArray A temporary array used for merging sorted halves.
     * @param left      The starting index of the portion of the array to sort.
     * @param right     The ending index of the portion of the array to sort.
     * @return The number of inversions found in this portion of the array.
     */
    private int mergeSortAndCount(int[] array, int[] tempArray, int left, int right) {
        int mid, inversions = 0;
        if (right > left) {
            // Find the middle point to divide the array into two halves.
            mid = (right + left) / 2;
            // Recursively sort the first half and count inversions.
            inversions += mergeSortAndCount(array, tempArray, left, mid);
            // Recursively sort the second half and count inversions.
            inversions += mergeSortAndCount(array, tempArray, mid + 1, right);
            // Merge the sorted halves and count any split inversions.
            inversions += mergeAndCount(array, tempArray, left, mid + 1, right);
        }
        return inversions;
    }

    /**
     * Merges two sorted halves of the array while counting the inversions where the element from the second half
     * is moved before the elements of the first half (split inversions).
     *
     * @param array     The original array containing both halves to merge.
     * @param tempArray A temporary array used for merging.
     * @param left      The starting index of the first half.
     * @param mid       The starting index of the second half.
     * @param right     The ending index of the second half.
     * @return The number of split inversions found during the merge.
     */
    private int mergeAndCount(int[] array, int[] tempArray, int left, int mid, int right) {
        int i = left; // Initial index of first subarray
        int j = mid;  // Initial index of second subarray
        int k = left; // Initial index of merged subarray
        int inversions = 0;

        // Merge the two halves into tempArray[]
        while ((i <= mid - 1) && (j <= right)) {
            if (array[i] <= array[j]) {
                tempArray[k++] = array[i++];
            } else {
                tempArray[k++] = array[j++];
                // Every time we choose an element from the second half,
                // we count inversions equal to the number of remaining elements in the first half.
                inversions += (mid - i);
            }
        }

        // Copy the remaining elements of the first half, if there are any.
        while (i <= mid - 1) {
            tempArray[k++] = array[i++];
        }

        // Copy the remaining elements of the second half, if there are any.
        while (j <= right) {
            tempArray[k++] = array[j++];
        }

        // Copy the merged elements back into the original array.
        for (i = left; i <= right; i++) {
            array[i] = tempArray[i];
        }

        return inversions;
    }
}
