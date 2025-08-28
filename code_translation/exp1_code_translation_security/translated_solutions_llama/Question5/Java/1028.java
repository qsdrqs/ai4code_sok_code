public class Main {

    /**
     * Reverses the given string.
     * 
     * @param s The string to be reversed.
     * @return The reversed string.
     */
    public static String reverse(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        return new String(arr);
    }

    /**
     * Converts an integer to a string.
     * 
     * @param n The integer to be converted.
     * @return The string representation of the integer.
     */
    public static String itoa(int n) {
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (n < 0) {
            n = -n;
            isNegative = true;
        }

        do {
            sb.append((char) (n % 10 + '0'));
            n /= 10;
        } while (n > 0);

        if (isNegative) {
            sb.append('-');
        }

        return sb.reverse().toString();
    }

    /**
     * Inserts a comma at the specified position in the string.
     * 
     * @param str The string to be modified.
     * @param pos The position where the comma is to be inserted.
     * @return The modified string with a comma at the specified position.
     */
    public static String insertComma(String str, int pos) {
        return str.substring(0, pos) + "," + str.substring(pos);
    }

    /**
     * Converts an integer to a string with commas.
     * 
     * @param num The integer to be converted.
     * @return The string representation of the integer with commas.
     */
    public static String intToString(int num) {
        String str = itoa(num);
        StringBuilder sb = new StringBuilder(str);

        for (int i = str.length() - 3; i > 0; i -= 3) {
            sb.insert(i, ",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 7000000;
        System.out.println(intToString(num));
    }
}