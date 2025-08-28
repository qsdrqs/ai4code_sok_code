/*  All required dependencies and imports are assumed to be present */

public class Main {

    /**
     * Converts an int to its String representation.
     * (Commas are intentionally omitted just like in the original C code.)
     */
    public static String convert(int num) {
        /*  
            In the C version the length of the resulting string was first
            computed with snprintf, and then a buffer was manually allocated.
            In Java we don’t need to do any of that—the standard library
            already provides the conversion and handles memory for us.
        */
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
    }
}