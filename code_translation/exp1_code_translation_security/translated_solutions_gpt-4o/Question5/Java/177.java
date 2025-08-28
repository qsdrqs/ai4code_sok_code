import java.util.ArrayList;

public class IntToStrWithCommas {

    public static String int2str(int num) {
        // Convert the integer to a string
        String str = Integer.toString(num);
        int length = str.length();

        // Calculate the new length of the string with commas
        int newLength;
        if (length % 3 == 0) {
            newLength = length + (length / 3) - 1;
        } else {
            newLength = length + (length / 3);
        }

        // Create a StringBuilder to build the result
        StringBuilder result = new StringBuilder(newLength);
        int counter = 0;

        // Iterate through the original string in reverse
        for (int i = length - 1; i >= 0; i--) {
            counter++;
            result.append(str.charAt(i));
            // Add a comma after every 3 digits, except at the end
            if (counter % 3 == 0 && i != 0) {
                result.append(',');
            }
        }

        // Reverse the result to get the final string
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        int num = 123456789;
        String result = int2str(num);
        System.out.println("Formatted number: " + result);
    }
}