public class Main {

    public static String intToString(int input) {
        // Convert the integer to a string
        String result = Integer.toString(input);

        // Print the result
        System.out.println("you have entered: " + result);

        // Return the result
        return result;
    }

    public static void main(String[] args) {
        // Call the intToString method with the input value
        intToString(7000000);
    }
}