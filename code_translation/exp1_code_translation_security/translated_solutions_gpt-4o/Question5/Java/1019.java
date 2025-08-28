import java.lang.Math;

public class Main {

    // Function to convert an integer to its string representation
    public static String stringRep(int num) {
        // Use Integer.toString to convert the number to a string
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        int leet = 1337;

        // Get the string representation of the number
        String leetStr = stringRep(leet);

        // Print the string representation
        System.out.println(leetStr);
    }
}