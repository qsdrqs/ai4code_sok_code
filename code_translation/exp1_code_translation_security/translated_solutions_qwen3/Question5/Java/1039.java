public class Main {

    // Translated from the C function `getLength`
    static int getLength(int num) {
        int len = 0;
        int numCopy = num;
        while (numCopy > 0) {
            numCopy = numCopy / 10;
            len += 1;
        }
        return len;
    }

    // Translated from the C function `convertToString`
    static String convertToString(int num) {
        // In C, this function initializes a char array and appends "test"
        // In Java, we can directly return the string "test"
        return "test";
    }

    // Main method equivalent to C's `main` function
    public static void main(String[] args) {
        int num = 7000000;

        // Print the length of the number
        System.out.print(getLength(num));

        // Print the result of convertToString(5)
        System.out.print(convertToString(5));
    }
}