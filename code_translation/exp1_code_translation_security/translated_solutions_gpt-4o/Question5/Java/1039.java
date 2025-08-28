import java.util.*;

public class Main {

    // Method to get the length of an integer
    public static int getLength(int num) {
        int len = 0;
        int numCopy = num;
        while (numCopy > 0) {
            numCopy = numCopy / 10;
            len = len + 1;
        }
        return len;
    }

    // Method to convert an integer to a string and append "test"
    public static String convertToString(int num) {
        String str = "";
        str += "test"; // Appending "test" to the string
        return str;
    }

    public static void main(String[] args) {
        int num = 7000000;

        // Print the length of the number
        System.out.println(getLength(num));

        // Print the result of convertToString
        System.out.println(convertToString(5));
    }
}