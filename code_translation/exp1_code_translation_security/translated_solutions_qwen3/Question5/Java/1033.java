public class Main {

    public static void main(String[] args) {
        int num = 7042;
        String str = numToStr(num);
        System.out.print("Output: " + str);
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     *
     * @param num The integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String numToStr(int num) {
        if (num == 0) {
            return "0";
        }

        String s = String.valueOf(num);
        boolean isNegative = false;

        // Handle negative numbers
        if (num < 0) {
            isNegative = true;
            s = s.substring(1); // Remove the negative sign
        }

        // Reverse the string to insert commas from the right
        StringBuilder reversed = new StringBuilder(s);
        reversed.reverse();

        // Build the result with commas inserted every 3 digits
        StringBuilder chunks = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            chunks.append(reversed.charAt(i));

            // Insert a comma after every 3 digits, except after the last digit
            if ((i + 1) % 3 == 0 && i != reversed.length() - 1) {
                chunks.append(',');
            }
        }

        // Reverse the result to get the correct order
        String reversedWithCommas = chunks.reverse().toString();

        // Reattach the negative sign if needed
        if (isNegative) {
            reversedWithCommas = "-" + reversedWithCommas;
        }

        return reversedWithCommas;
    }
}