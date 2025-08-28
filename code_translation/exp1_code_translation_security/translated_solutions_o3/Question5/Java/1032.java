/**
 * Direct Java translation of the given C program.
 * The logic is kept one-to-one with the original code
 * (even though Java’s libraries already have
 * `Integer.toString()` and `String.format("%,d", n)`).
 */
public class Main {

    /* ------------------------------------------------------------
     *  int_to_string – create a decimal representation of num
     * ------------------------------------------------------------
     */
    private static String intToString(int num) {

        /* exact port of the C algorithm, including the “0 is not
           represented” corner case that the original program had   */
        if (num == 0) {
            return "0";
        }

        int len = 0;
        int temp = num;

        /* count digits */
        while (temp != 0) {
            temp /= 10;
            len++;
        }

        /* build the string from right to left */
        char[] chars = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            chars[i] = (char) ((num % 10) + '0');
            num /= 10;
        }

        return new String(chars);
    }

    /* ------------------------------------------------------------
     *  int_to_string_with_commas – add grouping commas
     * ------------------------------------------------------------
     */
    private static String intToStringWithCommas(int num) {

        String str = intToString(num);
        int len = str.length();

        /* every three digits we need a comma, except before the
           leading group                                    */
        int commaCount = (len - 1) / 3;

        StringBuilder builder = new StringBuilder(len + commaCount);

        int j = 0;               // index in the original string
        boolean justInsertedComma = false;

        for (int i = 0; i < len + commaCount; i++) {

            /* replicate the C ‘if’ exactly */
            if ((len - j) % 3 == 0 && !justInsertedComma && i != 0) {
                builder.append(',');
                justInsertedComma = true;
            } else {
                builder.append(str.charAt(j));
                j++;
                if (justInsertedComma) {
                    justInsertedComma = false;
                }
            }
        }

        return builder.toString();
    }

    /* ------------------------------------------------------------
     *  main
     * ------------------------------------------------------------
     */
    public static void main(String[] args) {

        int num = 7_654_321;
        String formatted = intToStringWithCommas(num);
        System.out.println(formatted);  // prints 7,654,321
    }
}