import java.util.*;

public class Main {

    private static final int MAX_STR_LEN = 128;

    /**
     * Reverse a string
     *
     * @param str Input string
     * @return Reversed string
     */
    public static String reverseStr(String str) {
        int len = str.length();
        char[] newStr = new char[len];
        for (int i = 0; i < len; i++) {
            newStr[i] = str.charAt(len - i - 1);
        }
        return new String(newStr);
    }

    /**
     * Return a string representation of a signed integer
     *
     * @param num Input number as a signed integer
     * @return String representation of the signed integer
     */
    public static String strOfInt(int num) {
        char[] str = new char[MAX_STR_LEN];
        int i = 0;
        boolean isNeg = false;

        if (num < 0) {
            isNeg = true;
            num = -num;
        }

        do {
            str[i++] = (char) (num % 10 + '0');
            num /= 10;
        } while (num != 0);

        if (isNeg) {
            str[i++] = '-';
        }

        // Convert the char array to a string and reverse it
        return reverseStr(new String(str, 0, i));
    }

    public static void main(String[] args) {
        System.out.println(strOfInt(7000000));
    }
}