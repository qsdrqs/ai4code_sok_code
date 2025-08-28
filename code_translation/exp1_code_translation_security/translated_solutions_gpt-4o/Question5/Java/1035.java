public class Main {

    // Function to calculate 10^x
    public static int e10(int x) {
        int v = 1;
        for (int i = 0; i < x; i++) {
            v *= 10;
        }
        return v;
    }

    // Function to calculate the sign of a number
    public static int signum(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }
        return 1;
    }

    // Function to calculate the absolute value of a number
    public static int abs(int x) {
        return x * signum(x);
    }

    // Function to convert an integer to a formatted string
    public static String intToStr(int x) {
        char[] out = new char[16];
        int expOffset = 0;

        for (int i = 0; i < 16; i++) {
            int digit = i - expOffset;
            out[15 - i] = (char) (0x30 + abs((x % e10(digit + 1)) / e10(digit)));

            if (x % e10(digit + 1) == x) {
                if (x < 0) {
                    out[15 - (i + 1)] = '-';
                    i++;
                }
                return new String(out, 15 - i, i + 1);
            }

            if (i == 2 || i == 6 || i == 10) {
                out[15 - i - 1] = ',';
                i += 1;
                expOffset += 1;
            }
        }
        return new String(out);
    }

    public static void main(String[] args) {
        System.out.println(intToStr(55));
        System.out.println(intToStr(12345));
        System.out.println(intToStr(-55));
        System.out.println(intToStr(-123456789));
    }
}