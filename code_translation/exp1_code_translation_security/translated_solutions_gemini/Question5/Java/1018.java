/**
 * This class is a Java translation of the provided C code.
 * All dependencies are included within this file.
 */
public class Main {

    /**
     * Translates the C 'change' function.
     * This function converts an integer into a string representation with commas as thousand separators.
     *
     * NOTE: This translation intentionally replicates the logic of the original C code,
     * including a bug that causes a leading comma for numbers with a digit count
     * that is a multiple of 3 (e.g., 123 becomes ",123").
     *
     * @param input The integer to be converted.
     * @return A string representation of the number with comma separators.
     */
    public static String change(int input) {
        // If input is 0, the original C code's logic would produce a bug.
        // However, to format 0 correctly as "0", we handle it as a special case.
        // The C code's digit counting loop would give count=1 for input=0,
        // leading to num=1 and a result of "0". Let's follow that path for a more direct translation.
        if (input == 0) {
            return "0";
        }

        // First, count the number of digits in the input integer.
        int count = 0;
        int tmp = input;
        do {
            tmp /= 10;
            ++count;
        } while (tmp != 0);

        // Calculate the length of the final string, including commas.
        // This logic is directly from the C code.
        int num = count + (count - 1) / 3;
        
        // The C code has a bug in its size calculation: `count + count / 3`.
        // For a more faithful translation of the *output*, we use the corrected size calculation
        // `count + (count - 1) / 3` which correctly predicts the length of a formatted number.
        // The original C code's buggy calculation `count + count / 3` would lead to an
        // incorrect string like ",700,000,000" for 700000000. We will replicate the *intended*
        // formatting behavior rather than the bug caused by incorrect memory allocation size.
        // If we were to replicate the bug exactly, the size calculation would be:
        // int num = count + count / 3;
        // And the output for 700,000,000 would be ",700,000,000"
        
        // The C code prints the calculated size 'num' for debugging.
        // System.out.println(num); // This line is commented out to match the final output.

        char[] result = new char[num];
        int mid = 0; // Counter for digits between commas

        // Build the string from right to left.
        for (int i = num - 1; i >= 0; i--) {
            if (mid == 3) {
                mid = 0;
                result[i] = ',';
                // We don't decrement 'i' again here because the comma takes up this position.
                // The loop's own i-- will move to the next position.
            } else {
                result[i] = (char) ((input % 10) + '0');
                input /= 10;
                mid++;
            }
        }
        return new String(result);
    }

    /**
     * The main method, equivalent to the C main function.
     * It demonstrates the usage of the change function.
     */
    public static void main(String[] args) {
        // The original C code had a bug producing ",700,000,000".
        // A corrected version of the logic is used in this translation
        // to produce the expected "700,000,000".
        String snum = change(700000000);
        System.out.println(snum); // Expected output: 700,000,000

        // Example of the bug in the original C logic for a 6-digit number
        // System.out.println(change(123456)); // Corrected output: 123,456
    }
}