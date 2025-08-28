public class Main {

    // Method to convert an integer to a string manually
    public static String convertInt(int input) {
        char[] output = new char[9];
        int index = 0;

        while (input > 0) {
            output[index++] = (char) (input % 10 + '0');
            input /= 10;
        }

        // Reverse the array to get the correct order
        StringBuilder result = new StringBuilder();
        for (int i = index - 1; i >= 0; i--) {
            result.append(output[i]);
        }

        return result.toString();
    }

    // Method to convert an integer to a string using built-in functionality
    public static String convertIntAI(int input) {
        return Integer.toString(input);
    }

    public static void main(String[] args) {
        String output = convertIntAI(7000000);
        System.out.println(output);
    }
}