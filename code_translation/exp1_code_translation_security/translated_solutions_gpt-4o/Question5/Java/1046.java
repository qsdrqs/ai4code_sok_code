public class Main {

    // Method to convert an integer to a string and return the first character
    public static char convert(int num) {
        // Convert the integer to a string
        String str = Integer.toString(num);
        
        // Print the string representation of the number
        System.out.print(str);
        
        // Return the first character of the string
        return str.charAt(0);
    }

    public static void main(String[] args) {
        // Call the convert method with the number 100 and print the returned character
        System.out.print(convert(100));
    }
}