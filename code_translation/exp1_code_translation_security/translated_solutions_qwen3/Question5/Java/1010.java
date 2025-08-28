public class TranslatedCode {

    public static void main(String[] args) {
        System.out.printf("   %s%n", string_it(-123457));
    }

    public static String string_it(int value) {
        boolean negative = value < 0;
        int absValue = negative ? -value : value;
        String digits = Integer.toString(absValue);
        int commaCount = (digits.length() - 1) / 3;
        int end = digits.length();
        StringBuilder sb = new StringBuilder(digits);

        while (commaCount > 0) {
            int start = end - 3;
            System.out.println(sb.substring(start)); // Equivalent to printf("%s\n", start);
            sb.insert(start, ',');
            commaCount--;
            end = start;
        }

        if (negative) {
            sb.insert(0, '-');
        }

        return sb.toString();
    }
}