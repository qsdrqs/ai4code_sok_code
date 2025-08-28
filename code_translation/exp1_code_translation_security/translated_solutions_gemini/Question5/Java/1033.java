/**
 * This class contains the Java translation of the C code.
 */
public class CodeConverter {

    /**
     * Takes an integer and returns its string representation with commas as thousands separators.
     *
     * Note: The original C code's algorithm for inserting commas was logically flawed and
     * produced incorrect results. This Java version implements the *intended* functionality
     * correctly using a standard and robust approach.
     *
     * @param num The integer to be converted to a formatted string.
     * @return A string representation of the number with thousands separators.
     */
    public static String numToStr(int num) {
        // In C, sprintf was used to convert the number to a string.
        // String.valueOf() is the Java equivalent.
        String originalStr = String.valueOf(num);

        // Determine the part of the string that contains the actual digits,
        // excluding the negative sign for now.
        String numberPart = (num < 0) ? originalStr.substring(1) : originalStr;

        // If there are 3 or fewer digits, no commas are needed, so we can return early.
        if (numberPart.length() <= 3) {
            return originalStr;
        }

        // StringBuilder is used for efficient string construction, much like
        // manipulating a character array in C.
        StringBuilder result = new StringBuilder();

        // Prepend the negative sign if the number was negative.
        if (num < 0) {
            result.append('-');
        }

        int numberPartLength = numberPart.length();
        
        // Calculate the length of the first group of digits (from the left).
        // This can be 1, 2, or 3 digits.
        int firstGroupLength = numberPartLength % 3;
        if (firstGroupLength == 0) {
            firstGroupLength = 3;
        }

        // Append the first group of digits.
        result.append(numberPart.substring(0, firstGroupLength));

        // Iterate through the rest of the number string and append groups of 3,
        // each prefixed with a comma.
        for (int i = firstGroupLength; i < numberPartLength; i += 3) {
            result.append(',');
            result.append(numberPart.substring(i, i + 3));
        }

        return result.toString();
    }

    /**
     * The main method, translated from the C main function.
     */
    public static void main(String[] args) {
        // C code: int num = 7042;
        int num = 7042;
        
        // C code: char str[24]; numToStr(num, &str);
        // In Java, the method returns a new String, so we assign it to a variable.
        String str = numToStr(num);
        
        // C code: printf("Output: %s", str);
        // System.out.println is the standard way to print to the console in Java.
        System.out.println("Output: " + str);
    }
}