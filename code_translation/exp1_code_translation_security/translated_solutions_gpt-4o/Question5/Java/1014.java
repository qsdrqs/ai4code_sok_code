/*
A function that takes a signed integer num and returns a string representation of the integer
*/

public class IntegerToString {

    /*
    A function that reverses a string
    */
    public static String reverseString(String input) {
        char[] charArray = input.toCharArray();
        int start = 0;
        int end = charArray.length - 1;

        while (end > start) {
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }

        return new String(charArray);
    }

    public static String integerToString(int num) {
        StringBuilder result = new StringBuilder();
        boolean isNegative = false;

        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        do {
            int digit = num % 10;
            result.append((char) ('0' + digit));
            num /= 10;
        } while (num != 0);

        if (isNegative) {
            result.append('-');
        }

        return reverseString(result.toString());
    }

    public static void main(String[] args) {
        String num = integerToString(12345);
        System.out.println(num);

        num = integerToString(-12345);
        System.out.println(num);
    }
}