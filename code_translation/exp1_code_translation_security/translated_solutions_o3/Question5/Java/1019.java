// Main.java

public class Main {

    /*
     * Converts an integer to its string representation.
     * (Equivalent to the C function `string_rep`.)
     */
    public static String stringRep(int num) {
        // No need for manual allocation or log10 — just delegate to the JDK.
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        int leet = 1337;
        String leetStr = stringRep(leet);

        // Equivalent to `printf("%s", *leet_str);` in C.
        System.out.printf("%s", leetStr);
    }
}