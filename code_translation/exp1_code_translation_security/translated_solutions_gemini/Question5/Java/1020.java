public class Main {
    public static void main(String[] args) {
        // Define the integer you want to convert
        int number = -12;

        // In Java, a String is an object, not a character array.
        // We will create the string directly from the integer.
        String numberAsString;

        // Convert the integer to a string.
        // This is the Java equivalent of C's sprintf(string, "%d", number).
        numberAsString = Integer.toString(number);

        // An alternative and very common way to do the same conversion:
        // String numberAsString = String.valueOf(number);

        // Print the string to the console.
        // System.out.println automatically adds a newline character at the end.
        System.out.println(numberAsString);
    }
}