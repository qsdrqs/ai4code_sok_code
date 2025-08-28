public class Main {

    // Returns the number of digits in the supplied integer.
    public static int getLength(int num) {
        int len = 0;
        int numCopy = num;

        while (numCopy > 0) {
            numCopy /= 10;
            len++;
        }
        return len;
    }

    // Mimics the original C routine: always returns the string "test".
    // The int parameter is kept only to match the original signature.
    public static String convertToString(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 7_000_000;          // same value as in the C code
        System.out.print(getLength(num));   // prints 7
        System.out.print(convertToString(5)); // prints "test"
    }
}