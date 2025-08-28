public class Main {

    public static void main(String[] args) {
        System.out.println(intToStringWithCommas(7000000));
    }

    /**
     * Converts an integer to a string with commas separating every three digits.
     * Handles negative numbers and zero correctly.
     */
    public static String intToStringWithCommas(int value) {
        boolean isNegative = value < 0;
        if (isNegative) {
            value = -value;
        }

        // Handle the special case of zero
        if (value == 0) {
            return "0";
        }

        // Convert the number to a string
        String s = Integer.toString(value);

        // Use a StringBuilder to build the result in reverse order
        StringBuilder sb = new StringBuilder();

        int length = s.length();

        // Process the string from the end to the beginning
        for (int i = length - 1; i >= 0; i--) {
            sb.append(s.charAt(i));

            // Insert a comma after every 3 digits, except after the first digit
            int position = length - i;
            if (position % 3 == 0 && i != 0) {
                sb.append(',');
            }
        }

        // Reverse the result to get the correct order
        String result = sb.reverse().toString();

        // Add the negative sign if needed
        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }
}