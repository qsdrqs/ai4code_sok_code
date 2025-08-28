public class Translator {

    public static void main(String[] args) {
        System.out.println(intToStr(55));
        System.out.println(intToStr(12345));
        System.out.println(intToStr(-55));
        System.out.println(intToStr(-123456789));
    }

    // Helper function to compute 10^x
    public static int e10(int x) {
        return (int) Math.pow(10, x);
    }

    // Signum function: returns -1, 0, or 1
    public static int signum(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;
        return 1;
    }

    // Absolute value function
    public static int abs(int x) {
        return x * signum(x);
    }

    // Converts an integer to a string with commas as thousand separators
    public static String intToStr(int x) {
        if (x == 0) {
            return "0";
        }

        boolean isNegative = x < 0;
        x = abs(x);

        java.util.List<Integer> digits = new java.util.ArrayList<>();

        // Extract digits in reverse order (least significant to most)
        while (x > 0) {
            digits.add(x % 10);
            x /= 10;
        }

        StringBuilder sb = new StringBuilder();

        if (isNegative) {
            sb.append('-');
        }

        // Reconstruct the number in correct order and insert commas
        for (int i = digits.size() - 1; i >= 0; i--) {
            sb.append(digits.get(i));
            if (i != 0 && i % 3 == 0) {
                sb.append(',');
            }
        }

        return sb.toString();
    }
}