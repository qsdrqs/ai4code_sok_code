public class Main {

    // Equivalent to `char* int_to_string(int input)` in C
    // but idiomatically returns a String instead of a C-style char*
    public static String intToString(int input) {
        String result = Integer.toString(input);          // same as sprintf(buffer, "%d", input)
        System.out.printf("you have entered: %s", result); // same as printf in C
        return result;                                    // return the String (could be ignored)
    }

    public static void main(String[] args) {
        intToString(7_000_000); // 7,000,000
    }
}