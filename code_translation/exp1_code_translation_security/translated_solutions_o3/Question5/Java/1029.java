/*
 *  Direct translation of the original C program to Java.
 *  (No external dependencies are required.)
 */
public class Main {

    /**
     * Rough Java equivalent of the first C version.
     * NOTE: the original C implementation contains a logical flaw
     *       (it inspects output[i] before putting anything into it).
     *       A corrected, safe implementation is given here.
     */
    public static String convertInt(int input) {

        char[] buffer = new char[9];   // Enough for any 32-bit int except its sign
        int idx = 0;

        /* Produce digits in reverse order */
        do {
            buffer[idx++] = (char) (input % 10 + '0');
            input /= 10;
        } while (input != 0 && idx < buffer.length);

        /* Reverse the collected digits to obtain the proper order */
        for (int i = 0; i < idx / 2; ++i) {
            char tmp = buffer[i];
            buffer[i] = buffer[idx - 1 - i];
            buffer[idx - 1 - i] = tmp;
        }

        return new String(buffer, 0, idx);
    }

    /**
     * Java equivalent of the C function that used malloc + sprintf.
     * In Java we simply rely on String.format or Integer.toString.
     */
    public static String convertIntAI(int input) {
        return String.format("%d", input);   // Same semantics as sprintf("%d", …)
        // or: return Integer.toString(input);
    }

    public static void main(String[] args) {

        String output = convertIntAI(7_000_000);
        System.out.println(output);
    }
}