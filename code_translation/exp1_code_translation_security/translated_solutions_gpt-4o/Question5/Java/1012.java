import java.util.*;

public class Main {

    // Helper function to insert a comma into the string at a specific index
    public static String insert(String str, int index) {
        // Use substring to split the string and insert a comma
        return str.substring(0, index) + "," + str.substring(index);
    }

    // Function to generate the string representation of a number with commas
    public static String stringRepre(int num) {
        String strnum = Integer.toString(num); // Convert the number to a string
        int length = strnum.length(); // Original length of the string
        int commaCount = length / 3; // Number of commas to be inserted

        // Adjust the length to account for the commas
        int newLength = length + commaCount;

        // Use a StringBuilder for efficient string manipulation
        StringBuilder result = new StringBuilder(strnum);

        // Insert commas from right to left
        for (int i = 1; i <= commaCount; i++) {
            // Calculate the index to insert the comma
            int index = result.length() - 3 * i - (i - 1);
            if (index > 0) {
                result.insert(index, ',');
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Convert the number of command-line arguments to a string with commas
        int argc = args.length; // Number of command-line arguments
        String result = stringRepre(argc);

        // Print the result
        System.out.println(result);
    }
}