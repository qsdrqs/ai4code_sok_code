public class Main {

    public static void main(String[] args) {
        System.out.println("   " + stringIt(-123457));
    }

    // Return a string containing the expansion of the signed int
    public static String stringIt(int value) {
        // Even if 64 bit int... only about 18 characters.
        // Add room for sign and 6 commas
        // Far less than 30 total.
        // Could put in asserts here in case ints are crazy big
        StringBuilder returned = new StringBuilder(30);
        if (value < 0) {
            returned.append('-');
            value = -value;
        }
        String str = Integer.toString(value);
        returned.append(str);

        int length = str.length();
        int commaCount = (length - 1) / 3;

        while (commaCount > 0) {
            int start = returned.length() - 3 * commaCount;
            returned.insert(start, ',');
            commaCount--;
        }

        return returned.toString();
    }
}