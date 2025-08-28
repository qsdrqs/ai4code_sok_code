public class IntotostrConverter {

    // Method to convert integer to string
    public static String intotostr(int n) {
        return Integer.toString(n);
    }

    // Main method to test the conversion
    public static void main(String[] args) {
        int number = 123; // Example integer
        String result = intotostr(number);
        System.out.println("Converted string: " + result);
    }
}