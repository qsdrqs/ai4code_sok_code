import java.util.*;

public class Main {

    // Function to reverse a string
    public static void reverse(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    // Function to convert an integer to a string
    public static String itoa(int n) {
        StringBuilder sb = new StringBuilder();
        boolean isNegative = n < 0;
        if (isNegative) {
            n = -n; // Make n positive
        }

        do {
            sb.append((char) ('0' + (n % 10))); // Get next digit
            n /= 10; // Remove the last digit
        } while (n > 0);

        if (isNegative) {
            sb.append('-');
        }

        // Reverse the string to get the correct order
        sb.reverse();
        return sb.toString();
    }

    // Function to insert a comma at a specific position in a string
    public static String insertComma(String str, int pos) {
        return str.substring(0, pos) + "," + str.substring(pos);
    }

    // Function to convert an integer to a string with commas
    public static String intToString(int num) {
        String s = itoa(num);
        StringBuilder sb = new StringBuilder(s);

        // Insert commas every 3 digits from the right
        int length = sb.length();
        for (int i = length - 3; i > 0; i -= 3) {
            sb.insert(i, ',');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 7000;
        String result = intToString(num);
        System.out.println(result); // Output: 7,000
    }
}