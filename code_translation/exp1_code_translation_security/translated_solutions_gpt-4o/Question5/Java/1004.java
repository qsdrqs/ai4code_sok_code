public class IntegerToString {

    public static void main(String[] args) {
        int n = 0;
        String str = repr(n);
        System.out.println(str);
    }

    /*
    Function repr

    Takes in a signed integer and returns the string representation of that integer.
    */
    public static String repr(int n) {
        if (n == Integer.MIN_VALUE) {
            // When multiplied by -1 this overflows, so handle it separately.
            return "-2,147,483,648";
        }
        if (n == 0) {
            return "0";
        }

        StringBuilder str = new StringBuilder();
        int sign = 1;
        if (n < 0) {
            sign = -1;
            n *= -1;
        }

        int count = 0;
        while (n > 0) {
            if (count > 0 && count % 3 == 0) {
                str.append(',');
            }
            str.append(n % 10);
            n /= 10;
            count++;
        }

        if (sign == -1) {
            str.append('-');
        }

        return str.reverse().toString();
    }
}