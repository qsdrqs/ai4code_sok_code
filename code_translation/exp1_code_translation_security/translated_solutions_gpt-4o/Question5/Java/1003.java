public class IntToStringConverter {

    public static String int2str(int num) {
        // Convert the integer to a string using Java's built-in functionality
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        // Example usage
        int number = 12345;
        String result = int2str(number);
        System.out.println("Converted number: " + result);
    }
}