public class AddCommas {

    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str); // Output: 1,000,000
    }

    /**
     * Reverses the given string.
     * 
     * @param str The string to be reversed.
     * @return A new string that is the reverse of the input.
     */
    public static String reverse_str(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    /**
     * Converts the given integer into a string with commas added at appropriate positions.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the number with commas.
     */
    public static String addCommas(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder reversed = new StringBuilder();
        int count = 0;

        while (num != 0) {
            int digit = num % 10;
            reversed.append((char) ('0' + digit));
            num /= 10;
            count++;

            if (count == 3 && num != 0) {
                reversed.append(',');
                count = 0;
            }
        }

        return reverse_str(reversed.toString());
    }
}