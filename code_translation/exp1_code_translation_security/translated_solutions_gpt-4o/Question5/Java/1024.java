/*
Write a Java program to convert int into a string
*/

public class IntToString {
    public static String intToStr(int num) {
        // Convert the integer to a string using the built-in method
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        int number = 1234;
        String result = intToStr(number);
        System.out.println("The string representation of the number is: " + result);
    }
}