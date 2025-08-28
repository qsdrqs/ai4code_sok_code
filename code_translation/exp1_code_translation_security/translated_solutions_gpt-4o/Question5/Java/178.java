import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(convertToString(2000));
    }

    public static String convertToString(int num) {
        // Convert the number to a string
        String string = Integer.toString(num);

        // Calculate the number of commas needed
        int numOfCommas = (int) Math.ceil((double) string.length() / 3.0) - 1;
        System.out.println(numOfCommas);

        // Create a StringBuilder to construct the new string with commas
        StringBuilder newString = new StringBuilder();

        // Iterate through the original string and add commas where necessary
        int count = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            newString.insert(0, string.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                newString.insert(0, ",");
            }
        }

        return newString.toString();
    }
}