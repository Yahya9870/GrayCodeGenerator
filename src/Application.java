import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class provides a text-based menu system for the console application. It demonstrates various algorithms by
 * allowing the user to interact with the system through a series of options.
 */
public class Application {

    // Initialization of algorithm classes
    private final PalindromeChecker palindromeChecker = new PalindromeChecker();
    private final EasyInversionCount easyInversionCount = new EasyInversionCount();
    private final FastInversionCount fastInversionCount = new FastInversionCount();
    private final GrayCodeGenerator grayCodeGenerator = new GrayCodeGenerator();

    /**
     * The start method initializes the application's loop, which presents the user with a set of options
     * and processes the user's input.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int[] array;

        // Main loop for the application
        while (!exit) {
            // Print the main menu
            System.out.println("Please choose an option:");
            System.out.println("1. Palindrome Check");
            System.out.println("2. Easy Inversion Count");
            System.out.println("3. Fast Inversion Count");
            System.out.println("4. Generate Gray Code");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                // Process the user's menu selection
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                // Switch statement to handle the user's choice
                switch (choice) {
                    case 1: // Palindrome checking
                        System.out.print("Enter a string to check for palindrome: ");
                        String input = scanner.nextLine();
                        boolean isPalindrome = palindromeChecker.isPalindrome(input);
                        System.out.println("The string is " + (isPalindrome ? "" : "not ") + "a palindrome.");
                        break;
                    case 2: // Easy inversion count
                        array = readIntArray(scanner);
                        if (array != null) {
                            int easyCount = easyInversionCount.countInversions(array);
                            System.out.println("Number of inversions: " + easyCount);
                        } else {
                            System.out.println("There was an error reading the array of numbers.");
                        }
                        break;
                    case 3: // Fast inversion count
                        array = readIntArray(scanner);
                        int fastCount = fastInversionCount.countInversions(array);
                        System.out.println("Number of inversions: " + fastCount);
                        break;
                    case 4: // Gray code generation
                        System.out.print("Enter the number of bits for Gray Code generation: ");
                        int bits = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        if (bits == 5 || bits == 7) {
                            grayCodeGenerator.generateAndPrintGrayCode(bits);
                        } else {
                            System.out.println("The number of bits must be 5 or 7.");
                        }
                        break;
                    case 5: // Exit the application
                        System.out.println("Exiting the application.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the wrong input
            } catch (NoSuchElementException e) {
                System.out.println("Input operation was cancelled.");
                break;
            }
        }
        scanner.close();
    }

    /**
     * Reads an array of integers from the console. It first asks for the length of the array, then reads that many
     * numbers from the user.
     *
     * @param scanner The scanner object to read the console input.
     * @return An array of integers read from the console.
     */
    private int[] readIntArray(Scanner scanner) {
        System.out.print("Enter the length of the array or press enter for default (8): ");
        String inputLength = scanner.nextLine().trim();
        int arrayLength = inputLength.isEmpty() ? 8 : Integer.parseInt(inputLength);

        int[] array = new int[arrayLength];
        System.out.println("Enter " + arrayLength + " numbers separated by space:");
        String line = scanner.nextLine();
        String[] tokens = line.split("\\s+");
        if (tokens.length > arrayLength) {
            System.out.println("You have entered more numbers than specified. Only the first " + arrayLength + " numbers will be considered.");
        }
        for (int i = 0; i < arrayLength; i++) {
            if (i < tokens.length) {
                try {
                    array[i] = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException e) {
                    System.out.println("One of the inputs is not a valid integer: " + tokens[i]);
                    return null; // You might want to handle this more gracefully
                }
            } else {
                array[i] = 0; // Default value if less than expected numbers are entered
            }
        }
        return array;
    }

    /**
     * The Main method that creates an instance of Application and starts it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Application().start();
    }
}
