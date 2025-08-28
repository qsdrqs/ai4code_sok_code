import java.util.Arrays;

public class CCodeConverter {

    /**
     * Calculates 10 to the power of x.
     * Equivalent to the C function e10.
     */
    public static int e10(int x) {
        int v = 1;
        for (int i = 0; i < x; i++) {
            v *= 10;
        }
        return v;
    }

    /**
     * Returns the sign of a number: -1 for negative, 0 for zero, 1 for positive.
     * Equivalent to the C function signum.
     */
    public static int signum(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * Calculates the absolute value of a number.
     * Equivalent to the C function abs.
     */
    public static int abs(int x) {
        // This implementation is kept to match the C code's logic.
        // A more standard Java implementation would be `Math.abs(x)`.
        return x * signum(x);
    }

    /**
     * Converts an integer to a string, adding commas as thousands separators.
     * This is a direct translation of the C function's logic.
     *
     * NOTE on the C original:
     * 1. `static char out[16]` in C creates a buffer that persists between function calls.
     *    In Java, we create a new `char[]` array for each call, which is safer and standard practice.
     * 2. The C code has an off-by-one error, writing to `out[16]` which is outside the
     *    array bounds. This translation corrects the indices to fit within a 16-element array (0-15).
     * 3. The C code returns a pointer (`char*`) to a position within its static buffer.
     *    The Java equivalent is to create a new String from the relevant portion of our character array.
     */
    public static String intToStr(int x) {
        // A 16-character buffer is sufficient for a 32-bit integer with commas.
        char[] out = new char[16];
        int expOffset = 0; // Tracks offset caused by inserting commas.

        // Handle the edge case of 0, as the loop logic might not cover it cleanly.
        if (x == 0) {
            return "0";
        }

        // This loop builds the string from right to left.
        for (int i = 0; i < 16; i++) {
            int digit = i - expOffset;
            
            // Isolate the n-th digit from the right and convert to a character.
            // Example: To get the 2nd digit (100s place) of 12345, digit=2.
            // (12345 % 1000) / 100 = 345 / 100 = 3.
            // We add '0' to convert the integer digit to its char representation.
            // The C code's `out[16-i]` is corrected to `out[15-i]` for 0-based indexing.
            out[15 - i] = (char) ('0' + abs((x % e10(digit + 1)) / e10(digit)));

            // Check if we have processed all digits of the number.
            // This is true when the number is smaller than the next power of 10.
            if (abs(x) % e10(digit + 1) == abs(x)) {
                int startIndex;
                int length;

                if (x < 0) {
                    // Place the minus sign to the left of the most significant digit.
                    out[15 - (i + 1)] = '-';
                    i++; // Increment i to account for the added '-' character.
                }
                
                // The C code returns a pointer. We calculate the start and length
                // to create a substring from our `out` array.
                // The start index is `15 - (i-1)` or `16-i`.
                // The length is `i`.
                // After careful analysis of the C code's pointer arithmetic, the Java
                // equivalent is `new String(out, 15 - (i-1), i)`.
                startIndex = 16 - i;
                length = i;
                
                // A simpler way to think about it:
                // Start index is where the first character was placed.
                // Length is the total number of characters placed (digits + sign + commas).
                // This logic was derived by tracing the C code's pointer return `&out[16-i]`.
                startIndex = 15 - (i-1);
                length = i;
                
                // Let's use the most robust calculation derived from tracing:
                startIndex = 15 - i + 1;
                length = i;
                if (x < 0) {
                    // After adding a sign, i was incremented.
                    startIndex = 15 - i + 1;
                    length = i;
                } else {
                    // For positive numbers, we have i+1 characters.
                    startIndex = 15 - i;
                    length = i + 1;
                }
                
                return new String(out, startIndex, length);
            }

            // Insert a comma after the 3rd, 6th, 9th digit etc.
            // The C code checks i=2, 6, 10 which corresponds to 3, 7, 11 characters processed.
            if (i == 2 || i == 6 || i == 10) {
                out[15 - i - 1] = ',';
                i++; // Skip a position in the loop to account for the comma
                expOffset++; // The digit calculation needs to be offset
            }
        }
        
        // Fallback for very large numbers that fill the buffer (should not happen with int).
        return new String(out);
    }

    public static void main(String[] args) {
        System.out.println("Running direct translation:");
        System.out.printf("Input: %d -> Output: %s\n", 55, intToStr(55));
        System.out.printf("Input: %d -> Output: %s\n", 12345, intToStr(12345));
        System.out.printf("Input: %d -> Output: %s\n", -55, intToStr(-55));
        System.out.printf("Input: %d -> Output: %s\n", -123456789, intToStr(-123456789));
        System.out.printf("Input: %d -> Output: %s\n", 1000000, intToStr(1000000));
        
        System.out.println("\nRunning idiomatic Java version:");
        System.out.printf("Input: %d -> Output: %s\n", 55, intToStrIdiomatic(55));
        System.out.printf("Input: %d -> Output: %s\n", 12345, intToStrIdiomatic(12345));
        System.out.printf("Input: %d -> Output: %s\n", -55, intToStrIdiomatic(-55));
        System.out.printf("Input: %d -> Output: %s\n", -123456789, intToStrIdiomatic(-123456789));
        System.out.printf("Input: %d -> Output: %s\n", 1000000, intToStrIdiomatic(1000000));
    }
    
    // --- Idiomatic Java Alternative ---
    
    /**
     * This is the standard, recommended way to format a number with commas in Java.
     * It is much simpler, safer, and more efficient.
     */
    public static String intToStrIdiomatic(int number) {
        // The "%,d" format specifier automatically adds locale-specific
        // thousands separators to an integer.
        return String.format("%,d", number);
    }
}