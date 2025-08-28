public class Main {

    /**
     * Returns 10 raised to the power of x.
     * 
     * @param x The exponent.
     * @return 10 raised to the power of x.
     */
    public static int e10(int x) {
        int v = 1;
        for (int i = 0; i < x; i++) {
            v *= 10;
        }
        return v;
    }

    /**
     * Returns the signum of x.
     * 
     * @param x The number.
     * @return -1 if x is negative, 0 if x is 0, and 1 if x is positive.
     */
    public static int signum(int x) {
        if (x < 0)
            return -1;
        if (x == 0)
            return 0;
        return 1;
    }

    /**
     * Returns the absolute value of x.
     * 
     * @param x The number.
     * @return The absolute value of x.
     */
    public static int abs(int x) {
        return x * signum(x);
    }

    /**
     * Converts an integer to a string with commas as thousand separators.
     * 
     * @param x The number.
     * @return A string representation of x with commas.
     */
    public static String intToStr(int x) {
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append('-');
            x = -x;
        }

        String str = Integer.toString(x);
        StringBuilder result = new StringBuilder();

        int count = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            result.insert(0, str.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                result.insert(0, ',');
                count = 0;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToStr(55));
        System.out.println(intToStr(12345));
        System.out.println(intToStr(-55));
        System.out.println(intToStr(-123456789));
    }
}