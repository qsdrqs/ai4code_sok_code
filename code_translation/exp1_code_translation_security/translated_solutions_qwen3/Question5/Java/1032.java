public class Main {

    /**
     * Converts an integer to its string representation without using built-in formatting functions.
     * This function mirrors the behavior of the original C function `int_to_string`.
     * 
     * @param num The integer to convert.
     * @return A string representation of the integer.
     */
    public static String intToString(int num) {
        int len = 0;
        int temp = num;

        // Calculate the number of digits in the number
        while (temp != 0) {
            temp /= 10;
            len++;
        }

        // Handle the special case of num == 0
        if (len == 0) {
            return "0";
        }

        char[] str = new char[len];

        // Fill the array from the end to the beginning
        for (int i = len - 1; i >= 0; i--) {
            str[i] = (char) ((num % 10) + '0');
            num /= 10;
        }

        return new String(str);
    }

    /**
     * Converts an integer to a string with commas inserted every three digits from the right.
     * This function mirrors the behavior of the original C function `int_to_string_with_commas`.
     * 
     * @param num The integer to convert.
     * @return A string representation of the integer with commas.
     */
    public static String intToStringWithCommas(int num) {
        String str = intToString(num);
        int len = str.length();

        // Calculate how many commas are needed
        int commaCount = (len - 1) / 3;

        // Create a new char array to hold the formatted string
        char[] newStr = new char[len + commaCount];

        int j = 0; // Index for the original string
        int k = 0; // Flag to control comma insertion

        for (int i = 0; i < newStr.length; i++) {
            if ((len - j) % 3 == 0 && k != 1 && i != 0) {
                newStr[i] = ',';
                k = 1;
            } else {
                newStr[i] = str.charAt(j);
                j++;
                if (k == 1) {
                    k = 0;
                }
            }
        }

        return new String(newStr);
    }

    /**
     * Main method to test the functionality.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int num = 7654321;
        String result = intToStringWithCommas(num);
        System.out.println(result); // Expected output: 7,654,321
    }
}