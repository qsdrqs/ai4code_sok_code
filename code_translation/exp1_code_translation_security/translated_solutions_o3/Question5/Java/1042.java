public class IntToStringWithCommas {

    /**
     * Converts an int to its decimal representation and inserts commas
     * every three digits, counting from the right (e.g. 1234567 → "1,234,567").
     * The method is a faithful translation of the original C routine.
     */
    public static String intToString(int num) {
        // Workspace buffer – 100 chars is plenty for any 32-bit int + commas
        char[] str = new char[100];

        // Indices and counters
        int i = 0;          // Position we are writing into (forward, reversed order)
        int count = 0;      // How many digits since the last comma was written
        int temp = num;     // Copy of the number we mutate while processing

        // Special-case 0 because the original C code would yield an empty string
        if (temp == 0) {
            return "0";
        }

        // Build the string reversed, inserting a comma after every 3 digits
        while (temp != 0) {
            str[i++] = (char) ('0' + (temp % 10));   // Write next digit
            temp /= 10;
            count++;

            if (count == 3 && temp != 0) {           // Add comma if needed
                str[i++] = ',';
                count = 0;
            }
        }

        // Reverse the buffer in-place up to position i
        int j = 0;
        int k = i - 1;
        while (j < k) {
            char t = str[j];
            str[j] = str[k];
            str[k] = t;
            j++;
            k--;
        }

        // Produce a String from the populated part of the char array
        return new String(str, 0, i);
    }

    public static void main(String[] args) {
        System.out.println(intToString(7_000_000));  // prints 7,000,000
    }
}