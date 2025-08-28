import java.util.ArrayList;

public class Main {

    // Function to convert an integer to a string
    public static String intToString(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder str = new StringBuilder();
        while (num != 0) {
            str.append(num % 10);
            num /= 10;
        }

        // Reverse the string since digits are appended in reverse order
        return str.reverse().toString();
    }

    // Function to convert an integer to a string with commas
    public static String intToStringWithCommas(int num) {
        String str = intToString(num);
        int len = str.length();
        int commaCount = (len - 1) / 3;

        // Use StringBuilder for efficient string manipulation
        StringBuilder newStr = new StringBuilder(len + commaCount);

        int j = 0;
        int k = 0;
        for (int i = 0; i < len + commaCount; i++) {
            if ((len - j) % 3 == 0 && k != 1 && i != 0) {
                newStr.append(',');
                k = 1;
            } else {
                newStr.append(str.charAt(j));
                j++;
                if (k == 1) {
                    k = 0;
                }
            }
        }

        return newStr.toString();
    }

    public static void main(String[] args) {
        int num = 7654321;
        String str = intToStringWithCommas(num);
        System.out.println(str);
    }
}