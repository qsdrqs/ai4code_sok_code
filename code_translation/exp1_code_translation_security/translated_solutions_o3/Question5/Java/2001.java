public class Main {

    /**
     * Converts an int to a comma-separated string (e.g. 1234567 → "1,234,567").
     */
    public static String intToString(int a) {
        // Step 1: convert the integer to its plain decimal text form
        String raw = Integer.toString(a);

        // Step 2: detect a leading minus sign (if the number is negative)
        boolean isNegative = raw.charAt(0) == '-';
        int startIndex = isNegative ? 1 : 0;      // index of first digit
        int digits     = raw.length() - startIndex;

        // Step 3: build the result with commas
        StringBuilder out = new StringBuilder(raw.length() + digits / 3);

        if (isNegative) {
            out.append('-');
        }

        for (int i = 0; i < digits; i++) {
            // Insert a comma if we are at a thousands boundary
            if (i != 0 && (digits - i) % 3 == 0) {
                out.append(',');
            }
            out.append(raw.charAt(startIndex + i));
        }

        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));
        System.out.println(intToString(700000));
        System.out.println(intToString(70000));
        System.out.println(intToString(7000));
        System.out.println(intToString(700));
        System.out.println(intToString(70));
        System.out.println(intToString(7));
        System.out.println(intToString(0));
        System.out.println(intToString(-7000000));
        System.out.println(intToString(-700000));
        System.out.println(intToString(-70000));
        System.out.println(intToString(-7000));
        System.out.println(intToString(-700));
        System.out.println(intToString(-70));
        System.out.println(intToString(-7));
        System.out.println(intToString(Integer.MAX_VALUE));
        System.out.println(intToString(Integer.MIN_VALUE));
    }
}