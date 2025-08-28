import java.util.*;

public class AddCommas {

    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str);
    }

    // Method to reverse a string
    public static String reverseStr(String str) {
        char[] charArray = str.toCharArray();
        int i = 0, j = charArray.length - 1;
        while (i < j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
        return new String(charArray);
    }

    // Method to add commas to an integer
    public static String addCommas(int num) {
        StringBuilder str = new StringBuilder();
        int count = 0;

        while (num != 0) {
            str.append(num % 10);
            num /= 10;
            count++;
            if (count == 3 && num != 0) {
                str.append(',');
                count = 0;
            }
        }

        return reverseStr(str.toString());
    }
}