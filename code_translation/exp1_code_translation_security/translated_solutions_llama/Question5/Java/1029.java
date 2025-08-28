public class Main {

    /**
     * Converts an integer to a string manually.
     * 
     * @param input The integer to convert.
     * @return A string representation of the input integer.
     */
    public static String convertInt(int input) {
        if (input == 0) {
            return "0";
        }

        StringBuilder output = new StringBuilder();
        boolean isNegative = false;

        if (input < 0) {
            input = -input;
            isNegative = true;
        }

        while (input > 0) {
            int digit = input % 10;
            output.insert(0, (char) (digit + 48));
            input /= 10;
        }

        if (isNegative) {
            output.insert(0, '-');
        }

        return output.toString();
    }

    /**
     * Converts an integer to a string using Java's built-in functionality.
     * 
     * @param input The integer to convert.
     * @return A string representation of the input integer.
     */
    public static String convertIntAI(int input) {
        return Integer.toString(input);
    }

    public static void main(String[] args) {
        String output = convertIntAI(7000000);
        System.out.println(output);
    }
}