public class Main {

    public static void main(String[] args) {
        // Example usage: convert 7000000 to a formatted string
        System.out.println(intToString(7000000));
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     * 
     * @param num The integer to convert.
     * @return A string representation of the number with commas.
     */
    public static String intToString(int num) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int temp = num;

        // Handle the special case of zero
        if (temp == 0) {
            sb.append('0');
        } else {
            // Process each digit from right to left
            while (temp != 0) {
                // Append the current digit as a character
                sb.append((char) ((temp % 10) + '0'));
                temp /= 10;
                count++;

                // Insert a comma every 3 digits, except after the last group
                if (count == 3 && temp != 0) {
                    sb.append(',');
                    count = 0;
                }
            }
        }

        // Reverse the string to get the correct order
        return sb.reverse().toString();
    }
}