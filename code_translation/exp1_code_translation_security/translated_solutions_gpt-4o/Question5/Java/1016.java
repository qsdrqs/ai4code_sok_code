import java.util.ArrayList;

public class IntToStringWithCommas {

    public static String intToString(int num) {
        // Initialize variables
        int temp = num;
        int len = 0;
        int counter = 0;

        // Calculate the length of the string representation, including commas
        while (temp != 0) {
            temp = temp / 10;
            len++;
            counter++;
            // Add a comma for the first 4 digits, then every 3 digits after
            if (counter == 4) {
                len++;
                counter = 1;
            }
        }

        // If the number is negative, account for the minus sign
        if (num < 0) {
            len++; // +1 for the minus sign character
        }

        // Use a StringBuilder to construct the string
        StringBuilder str = new StringBuilder(len);
        int count = 0;

        // Handle negative numbers
        if (num < 0) {
            str.append('-');
            num = -num; // Convert to positive for processing
        }

        // Create a list to store the digits and commas
        ArrayList<Character> charList = new ArrayList<>();

        // Extract digits and add commas
        while (num != 0) {
            count++;
            if (count == 4) {
                charList.add(',');
                count = 1;
            }
            charList.add((char) ((num % 10) + '0'));
            num = num / 10;
        }

        // Reverse the list and append to the StringBuilder
        for (int i = charList.size() - 1; i >= 0; i--) {
            str.append(charList.get(i));
        }

        return str.toString();
    }

    public static void main(String[] args) {
        int num = -10099870;
        String str = intToString(num);
        System.out.println(str);
    }
}