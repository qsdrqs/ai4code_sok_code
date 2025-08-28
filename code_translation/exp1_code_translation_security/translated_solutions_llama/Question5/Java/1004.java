public class Main {

    private static final int MAX_DIGITS = 100;

    /**
     * Function repr
     * 
     * Takes in a signed integer and returns the string representation of that integer.
     * 
     * @param n The input integer.
     * @return The string representation of the integer.
     */
    public static String repr(int n) {
        if (n == Integer.MIN_VALUE) {
            // When multiplied by -1 this overflows, so handle it separately.
            return "-2,147,483,648";
        }
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (n < 0) {
            isNegative = true;
            n = Math.abs(n);
        }

        while (n > 0) {
            sb.append(n % 10);
            if ((sb.length() + 1) % 4 == 0 && n > 10) {
                sb.append(',');
            }
            n /= 10;
        }

        if (isNegative) {
            sb.append('-');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int n = 0;
        String str = repr(n);
        System.out.println(str);
    }
}