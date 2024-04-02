import java.util.Arrays;
import java.util.List;

// This class is responsible for generating Binary Reflected Gray Code sequences
// and simulating a scenario involving a group of characters, the Klutzomaniacs.
public class GrayCodeGenerator {

    // Extend the array to include Fitz and Giggles for a total of 7 Klutzomaniacs.
    private static final String[] klutzomaniacs = {
            // Array of names representing the Klutzomaniacs, including two additional characters.
            "Axel", "Boxo", "Crunchy", "Doofus", "Enzo", "Fitz", "Giggles"
    };

    // Method to generate and print Gray Code for a specified number of bits.
    public void generateAndPrintGrayCode(int bits) {
        String[] grayCodes = generateGrayCode(bits);
        printTable(grayCodes);
    }

    // Generates Gray Code sequence for a given number of bits.
    static String[] generateGrayCode(int bits) {
        int numCodes = 1 << bits; // 2^bits
        String[] grayCodes = new String[numCodes];
        // Generate Gray codes by XORing each number with itself shifted right by one bit.
        for (int i = 0; i < numCodes; i++) {
            // Convert to binary and XOR to get the Gray code.
            grayCodes[i] = Integer.toBinaryString(i ^ (i >> 1));
            // Pad with leading zeros to maintain consistent bit length.
            while (grayCodes[i].length() < bits) {
                grayCodes[i] = "0" + grayCodes[i];
            }
        }
        return grayCodes;
    }

    // Prints the Gray Code sequence along with Klutzomaniacs' actions in a table format.
    private static void printTable(String[] grayCodes) {
        // Calculate width for formatting based on the longest name.
        int klutzomaniacsWidth = getMaxKlutzomaniacsWidth();

        // Print the header of the table.
        System.out.printf("%5s | %7s | %-" + klutzomaniacsWidth + "s | %s%n", "Index", "Gray", "Klutzomaniacs Riding", "Action");
        // Initialize with a string of zeros equal to the number of bits.
        String previousCode = "0000000"; // Starting with all zeros for 7 bits.
        // Loop through all Gray codes to print the table.
        for (int index = 0; index < grayCodes.length; index++) {
            String currentCode = grayCodes[index];
            String riders = getRiders(currentCode);
            String action = getAction(previousCode, currentCode);
            System.out.printf("%5d | %7s | %-" + klutzomaniacsWidth + "s | %s%n", index, currentCode, riders, action);
            previousCode = currentCode;
        }
    }

    // Determines the maximum width needed to print all Klutzomaniacs riding the tricycle.
    private static int getMaxKlutzomaniacsWidth() {
        // Find the maximum name length, double it (for a pair), and add separator lengths.
        return Arrays.stream(klutzomaniacs)
                .mapToInt(String::length)
                .max()
                .orElse(0) // Use a default value if klutzomaniacs array is empty
                * 2 // Maximum possible riders (each name could potentially be there with a ", " separator)
                + " & ".length() * (klutzomaniacs.length - 1); // Account for separators between names
    }


    // Based on the current Gray code, determines which Klutzomaniacs are riding.
    private static String getRiders(String grayCode) {
        StringBuilder riders = new StringBuilder();
        // Append the names of Klutzomaniacs that correspond to '1' bits in the Gray code.
        for (int i = 0; i < grayCode.length(); i++) {
            if (grayCode.charAt(i) == '1') {
                if (riders.length() > 0) {
                    riders.append(", ");
                }
                riders.append(klutzomaniacs[i]);
            }
        }
        // Return the list of riders or 'Empty Tricycle' if none.
        return riders.length() > 0 ? riders.toString() : "Empty Tricycle";
    }

    // Determines the action taken (joining or leaving) based on the difference between
    // the current and previous Gray codes.
    private static String getAction(String previousCode, String currentCode) {
        StringBuilder action = new StringBuilder();
        // Check each bit position for a change and append the corresponding action.
        for (int i = 0; i < currentCode.length(); i++) {
            if (previousCode.charAt(i) != currentCode.charAt(i)) {
                if (action.length() > 0) {
                    action.append(", ");
                }
                // Append the name and whether they join or leave.
                action.append(klutzomaniacs[i]).append(currentCode.charAt(i) == '1' ? " Joins" : " Leaves");
            }
        }
        // Return the actions taken or 'No Change' if there were none.
        return action.length() > 0 ? action.toString() : "No Change";
    }
}
