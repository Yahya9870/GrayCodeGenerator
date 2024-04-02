/**
 * This class provides methods to check if a given string is a palindrome.
 */
public class PalindromeChecker {

    /**
     * Determines whether a given string is a palindrome.
     *
     * @param input The string to check.
     * @return {@code true} if the string is a palindrome, {@code false} otherwise.
     */
    public boolean isPalindrome(String input) {
        // Sanitize the input by removing non-alphanumeric characters and converting to lower case.
        String sanitizedInput = sanitizeString(input);
        // Recursively check if the sanitized string is a palindrome.
        return checkPalindrome(sanitizedInput, 0, sanitizedInput.length() - 1);
    }

    /**
     * Removes all non-alphanumeric characters from the string and converts it to lower case.
     *
     * @param input The string to sanitize.
     * @return A sanitized version of the string.
     */
    private String sanitizeString(String input) {
        // Replace all non-alphanumeric characters with an empty string.
        return input.replaceAll("[\\W_]+", "").toLowerCase();
    }

    /**
     * Recursively checks if a portion of a string is a palindrome.
     *
     * @param input The string to check.
     * @param left  The index of the leftmost character to check.
     * @param right The index of the rightmost character to check.
     * @return {@code true} if the specified portion of the string is a palindrome, {@code false} otherwise.
     */
    private boolean checkPalindrome(String input, int left, int right) {
        // If the pointers have met or crossed, all characters have been checked and the string is a palindrome.
        if (left >= right) {
            return true;
        }
        // If the characters at the pointers do not match, the string is not a palindrome.
        if (input.charAt(left) != input.charAt(right)) {
            return false;
        }
        // Move the pointers closer and check the next pair of characters.
        return checkPalindrome(input, left + 1, right - 1);
    }
}
