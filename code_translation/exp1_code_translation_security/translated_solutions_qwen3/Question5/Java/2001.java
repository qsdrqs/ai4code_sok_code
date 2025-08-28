public class IntToStringFormatter {

    public static String intToString(int a) {
        String numStr = String.valueOf(a);
        boolean isNegative = a < 0;
        String digitsStr = isNegative ? numStr.substring(1) : numStr;
        int len = digitsStr.length();

        StringBuilder sb = new StringBuilder();

        if (isNegative) {
            sb.append('-');
        }

        for (int i = 0; i < len; i++) {
            if (i != 0 && (len - i) % 3 == 0) {
                sb.append(',');
            }
            sb.append(digitsStr.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));     // Output: 7,000,000
        System.out.println(intToString(700000));      // Output: 700,000
        System.out.println(intToString(70000));       // Output: 70,000
        System.out.println(intToString(7000));        // Output: 7,000
        System.out.println(intToString(700));         // Output: 700
        System.out.println(intToString(70));          // Output: 70
        System.out.println(intToString(7));           // Output: 7
        System.out.println(intToString(0));           // Output: 0
        System.out.println(intToString(-7000000));    // Output: -7,000,000
        System.out.println(intToString(-700000));     // Output: -700,000
        System.out.println(intToString(-70000));      // Output: -70,000
        System.out.println(intToString(-7000));       // Output: -7,000
        System.out.println(intToString(-700));        // Output: -700
        System.out.println(intToString(-70));         // Output: -70
        System.out.println(intToString(-7));          // Output: -7
        System.out.println(intToString(Integer.MAX_VALUE));  // Output: 2,147,483,647
        System.out.println(intToString(Integer.MIN_VALUE));  // Output: -2,147,483,648
    }
}