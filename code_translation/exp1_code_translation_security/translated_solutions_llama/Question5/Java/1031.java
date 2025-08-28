public class Main {

    /**
     * Converts an integer to a string and prints it.
     * 
     * @param input The integer to be converted.
     * @return The string representation of the input integer.
     */
    public static String intToString(int input) {
        String buffer = Integer.toString(input);
        
        System.out.println("You have entered: " + buffer);
        
        return buffer;
    }

    public static void main(String[] args) {
        intToString(7000000);
    }
}