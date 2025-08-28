/*
Write a Java program to convert int into string
*/

public class IntToString {
    
    public static String intToStr(int n) {
        return String.valueOf(n);
    }
    
    // Alternative method using Integer.toString()
    public static String intToStrAlternative(int n) {
        return Integer.toString(n);
    }
    
    // Alternative method using String concatenation
    public static String intToStrConcat(int n) {
        return "" + n;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        int number = 123;
        
        System.out.println("Original number: " + number);
        System.out.println("Converted to string: " + intToStr(number));
        System.out.println("Alternative method: " + intToStrAlternative(number));
        System.out.println("Concatenation method: " + intToStrConcat(number));
    }
}