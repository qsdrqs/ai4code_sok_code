public class Main {

    // Equivalent of the C function 'convertInt'
    public static String convertInt(int input) {
        char[] output = new char[9];  // Matches C's char output[9]

        // This loop will not execute in Java because output is initialized to '\0'
        for (int i = 0; output[i] != '\0'; i++) {
            output[i] = (char) (input % 10 + '0');  // Convert digit to char
            input /= 10;
        }

        return new String(output);  // Convert char array to String
    }

    // Equivalent of the C function 'convertIntAI'
    public static String convertIntAI(int input) {
        // In C, this uses malloc and sprintf, which is equivalent to:
        return String.format("%d", input);  // Java's equivalent of sprintf
    }

    // Main method
    public static void main(String[] args) {
        String output = convertIntAI(7000000);
        System.out.print(output);  // Java's equivalent of printf
    }
}