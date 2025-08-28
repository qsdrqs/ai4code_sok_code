import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Declare variables
        long value;
        int c1, v1, len;
        String vstring;
        char[] commas = new char[14]; // Matches C's char commas[14]

        // Initialize random number generator
        Random rand = new Random();
        // Generate a random number in the range [0, 32767] to match C's rand()
        value = rand.nextInt(32768);

        // Print the original value
        System.out.printf("Before: \t%d%n", value);

        // Convert the value to a string
        vstring = String.valueOf(value);
        len = vstring.length();

        // Initialize loop counters
        c1 = v1 = 0;

        // Process each character in the string
        while (v1 < vstring.length()) {
            if ((len % 3) != 0) {
                // If not a multiple of 3, just copy the digit
                commas[c1] = vstring.charAt(v1);
            } else {
                // If a multiple of 3, insert a comma (if not the first group)
                if (c1 != 0) {
                    commas[c1] = ',';
                    c1++;
                }
                // Copy the current digit
                commas[c1] = vstring.charAt(v1);
            }
            // Move to the next position in the commas array
            c1++;
            // Move to the next character in the value string
            v1++;
            // Decrease the length counter
            len--;
        }

        // Convert the commas array to a string up to the current length
        String commasStr = new String(commas, 0, c1);

        // Print the formatted result
        System.out.printf("with: \t%s%n", commasStr);
    }
}