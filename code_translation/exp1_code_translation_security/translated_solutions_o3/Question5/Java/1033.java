public class Main {

    /**
     * Converts an int to a character sequence that contains
     * commas every three digits – exactly what the original C
     * function did.
     *
     * @param num the number to convert
     * @return    the formatted string
     */
    public static String numToStr(int num) {

        // Special-case zero (the C version just returned)
        if (num == 0) {
            return "0";
        }

        // Work with the absolute value, remember the sign
        boolean negative = num < 0;
        int abs = Math.abs(num);

        // Get the digits without commas first
        String digits = Integer.toString(abs);
        int len      = digits.length();

        // The result will be pieced together in a StringBuilder
        StringBuilder result = new StringBuilder();

        /*
         * How many digits will appear before the first comma?
         * e.g. 12,345 -> 2 digits before first comma
         *      1,234  -> 1 digit  before first comma
         *      123    -> no commas at all
         */
        int firstGroup = len % 3;
        if (firstGroup == 0) {
            firstGroup = 3;          // groups are 3-3-3-…
        }

        // Write the first (possibly short) group
        result.append(digits, 0, firstGroup);

        // Write the remaining groups, each preceded by a comma
        for (int i = firstGroup; i < len; i += 3) {
            result.append(',');
            result.append(digits, i, i + 3);
        }

        // Put the minus sign back if the original number was negative
        if (negative) {
            result.insert(0, '-');
        }

        return result.toString();
    }

    public static void main(String[] args) {

        int num = 7042;                // same test case as in the C code
        String formatted = numToStr(num);

        System.out.println("Output: " + formatted);
    }
}