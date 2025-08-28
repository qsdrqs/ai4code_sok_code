import java.util.ArrayList;

public class Main {

    // Function to calculate the number of digits in a number
    public static int numDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int cnt = 0;
        while (n != 0) {
            n = n / 10;
            cnt++;
        }
        return cnt;
    }

    // Function to return a formatted string with commas
    public static String returnString(int num) {
        int numDigits = numDigits(num);
        ArrayList<Character> ret = new ArrayList<>();

        // Convert the number to a string
        String str = Integer.toString(num);
        int strIndex = str.length() - 1; // Start from the last digit of the number
        int i = 0;

        // Iterate through the number and add commas at every third index
        while (strIndex >= 0) {
            if (i > 0 && i % 3 == 0) {
                ret.add(0, ','); // Add a comma at the correct position
            }
            ret.add(0, str.charAt(strIndex)); // Add the digit
            strIndex--;
            i++;
        }

        // Convert the ArrayList to a String
        StringBuilder result = new StringBuilder();
        for (char c : ret) {
            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int num = 7000000;
        String str = returnString(num);
        System.out.println(str);
    }
}