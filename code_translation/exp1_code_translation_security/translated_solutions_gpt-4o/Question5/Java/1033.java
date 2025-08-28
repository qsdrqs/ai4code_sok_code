import java.util.Arrays;

public class Main {

    public static void numToStr(int num, char[] str) {
        // Convert the number to a string
        String numStr = Integer.toString(num);
        int length = numStr.length();
        int numCommas = 0;

        if (num == 0) {
            // If the number is 0, just return "0"
            str[0] = '0';
            str[1] = '\0';
            return;
        } else if (num > 0) {
            // Calculate the number of commas needed
            numCommas = (length - 1) / 3;
            int digitsMoved = 0;

            // Create a new array to hold the result with commas
            char[] result = new char[length + numCommas];
            int resultIndex = result.length - 1;

            // Iterate through the number string from the end
            for (int i = length - 1; i >= 0; i--) {
                if (digitsMoved < 3) {
                    result[resultIndex--] = numStr.charAt(i);
                    digitsMoved++;
                } else {
                    result[resultIndex--] = ',';
                    result[resultIndex--] = numStr.charAt(i);
                    digitsMoved = 1;
                }
            }

            // Copy the result back to the original str array
            System.arraycopy(result, 0, str, 0, result.length);
            str[result.length] = '\0'; // Null-terminate the string
        } else {
            // Handle negative numbers
            numCommas = (length - 2) / 3;
            int digitsMoved = 0;

            // Create a new array to hold the result with commas
            char[] result = new char[length + numCommas];
            int resultIndex = result.length - 1;

            // Iterate through the number string from the end
            for (int i = length - 1; i > 0; i--) {
                if (digitsMoved < 3) {
                    result[resultIndex--] = numStr.charAt(i);
                    digitsMoved++;
                } else {
                    result[resultIndex--] = ',';
                    result[resultIndex--] = numStr.charAt(i);
                    digitsMoved = 1;
                }
            }

            // Add the negative sign
            result[resultIndex] = '-';

            // Copy the result back to the original str array
            System.arraycopy(result, 0, str, 0, result.length);
            str[result.length] = '\0'; // Null-terminate the string
        }
    }

    public static void main(String[] args) {
        int num = 7042;
        char[] str = new char[24]; // Allocate enough space for the result

        numToStr(num, str);

        // Convert the char array to a string and print the result
        String result = new String(str).trim();
        System.out.println("Output: " + result);
    }
}