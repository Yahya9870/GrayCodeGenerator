/**
 * This class provides a method to count the number of inversions in an array.
 * An inversion is defined as a pair of indices (i, j) such that i < j and array[i] > array[j].
 */
public class EasyInversionCount {

    /**
     * Counts the number of inversions in the given array using a brute-force approach.
     *
     * @param array The array to count inversions in.
     * @return The number of inversions found in the array.
     */
    public int countInversions(int[] array) {
        // Initialize inversion count to 0.
        int inversions = 0;

        // Iterate over each element of the array, excluding the last one.
        for (int i = 0; i < array.length - 1; i++) {
            // For each element, compare it with all subsequent elements.
            for (int j = i + 1; j < array.length; j++) {
                // If an inversion is found (i.e., a larger element precedes a smaller one), increment the inversion count.
                if (array[i] > array[j]) {
                    inversions++;
                }
            }
        }

        // Return the total count of inversions.
        return inversions;
    }
}

