public class Main {
    public static void main(String[] args) {
        // Test the function with the same input as in the C code
        System.out.print(numToString(-5305000));
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     * Handles negative numbers by prepending a '-' sign.
     *
     * @param num The integer to convert.
     * @return A formatted string representation of the number.
     */
    public static String numToString(int num) {
        // Handle the special case of zero
        if (num == 0) {
            return "0";
        }

        // Determine if the number is negative
        boolean isNeg = num < 0;
        num = Math.abs(num);

        // Count the number of digits in the number
        int totalDigits = 0;
        int curNum = num;
        while (curNum > 0) {
            curNum /= 10;
            totalDigits++;
        }

        // Calculate the number of commas needed
        int numCommas = (totalDigits - 1) / 3;

        // Calculate the total length of the resulting string
        int strLen = totalDigits + numCommas;
        if (isNeg) {
            strLen += 1; // Account for the '-' sign
        }

        // Create a character array to hold the formatted string
        char[] str = new char[strLen];

        // Reset current number and counters
        curNum = num;
        int currentIndex = 0;
        int digitsCount = 0;

        // Build the string from right to left
        while (curNum > 0) {
            char curChar = (char) ((curNum % 10) + '0');
            str[strLen - currentIndex - 1] = curChar;
            curNum /= 10;
            currentIndex++;
            digitsCount++;

            // Insert a comma every 3 digits (except after the last digit)
            if (digitsCount % 3 == 0 && currentIndex < strLen) {
                str[strLen - currentIndex - 1] = ',';
                currentIndex++;
            }
        }

        // Add the negative sign if needed
        if (isNeg) {
            str[0] = '-';
        }

        // Convert the character array to a string and return
        return new String(str);
    }
}