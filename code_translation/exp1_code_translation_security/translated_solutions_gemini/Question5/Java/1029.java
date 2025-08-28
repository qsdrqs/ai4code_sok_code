/**
 * This class contains the Java translation of the provided C code.
 */
public class CtoJavaConverter {

    /**
     * A corrected translation of the C function `convertInt`.
     *
     * NOTE: The original C function is critically flawed for several reasons:
     * 1.  **Undefined Behavior**: It returns a pointer to `output`, which is a local variable.
     *     This memory is deallocated when the function exits, so the caller receives a "dangling pointer"
     *     to garbage memory. This is a major bug.
     * 2.  **Faulty Loop**: The loop condition `output[i] != '\0'` reads from an uninitialized
     *     array, which is also undefined behavior. The loop may not run at all.
     * 3.  **Incorrect Order**: Even if it worked, the algorithm would produce the number's digits
     *     in reverse order (e.g., 123 would become "321").
     *
     * This Java version corrects these issues by implementing the *intended* logic: manually
     * converting an integer to a string.
     *
     * @param input The integer to convert.
     * @return The string representation of the integer.
     */
    public static String convertInt(int input) {
        // Handle the edge case of 0 separately.
        if (input == 0) {
            return "0";
        }

        // StringBuilder is efficient for creating strings in a loop.
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (input < 0) {
            isNegative = true;
            // Work with a positive number to avoid issues with Integer.MIN_VALUE.
            // Using long to safely negate Integer.MIN_VALUE.
            long num = -((long)input); 
            while (num > 0) {
                // (num % 10) gets the last digit.
                // Adding '0' converts the digit (0-9) to its char equivalent ('0'-'9').
                sb.append((char) ((num % 10) + '0'));
                num /= 10;
            }
        } else {
            int num = input;
            while (num > 0) {
                sb.append((char) ((num % 10) + '0'));
                num /= 10;
            }
        }

        if (isNegative) {
            sb.append('-');
        }

        // The digits were appended in reverse order, so we reverse the string.
        return sb.reverse().toString();
    }

    /**
     * A direct translation of the C function `convertIntAI`.
     *
     * The C function uses `sprintf` to format an integer into a string, which is a
     * standard library function. The equivalent and idiomatic way to do this in Java
     * is to use `String.valueOf()` or `Integer.toString()`.
     *
     * @param input The integer to convert.
     * @return The string representation of the integer.
     */
    public static String convertIntAI(int input) {
        // This is the standard, most efficient way to convert an int to a String in Java.
        return String.valueOf(input);
    }

    /**
     * The main method, equivalent to the C `main` function.
     * This is the entry point of the Java program.
     */
    public static void main(String[] args) {
        // Call the function and store the result in a String variable.
        String output = convertIntAI(7000000);
        
        // Print the result to the console. System.out.println is the Java
        // equivalent of C's printf for printing strings followed by a newline.
        System.out.println(output);

        // Example using the other function to show it works as well.
        System.out.println(convertInt(-12345));
        System.out.println(convertInt(0));
    }
}