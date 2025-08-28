import java.util.*;

public class Main {

    public static String intToString(int num) {
        StringBuilder str = new StringBuilder();
        int count = 0;

        // Handle the case where num is 0
        if (num == 0) {
            return "0";
        }

        while (num != 0) {
            // Append the last digit of num to the string
            str.append(num % 10);
            num = num / 10;
            count++;

            // Add a comma after every 3 digits, except for the last group
            if (count == 3 && num != 0) {
                str.append(',');
                count = 0;
            }
        }

        // Reverse the string to get the correct order
        return str.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));
    }
}