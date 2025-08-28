import java.util.Arrays;

public class Stringify {
    private static final int MAX_LEN = 14;

    public static int stringify(int i, char[] buf) {
        char[] tmp = new char[MAX_LEN];
        Arrays.fill(tmp, '0');
        int j = (i >= 0) ? i : -i;
        int k = MAX_LEN - 1;
        int c = 0;

        while (j > 0 && k >= 0) {
            if (c != 0 && c % 3 == 0) {
                tmp[k] = ',';
                k--;
                c = 0;
            }
            int digit = j % 10;
            char d_char = (char) (digit + '0');
            tmp[k] = d_char;
            j /= 10;
            k--;
            c++;
        }

        if (i < 0) {
            tmp[k] = '-';
            k--;
        }

        // Simulate the original C loop, but avoid array index out of bounds
        for (int n = k; n < MAX_LEN; n++) {
            if (n + 1 < MAX_LEN) {
                buf[n - k] = tmp[n + 1];
            }
        }

        return MAX_LEN - 1 - k;
    }

    public static void main(String[] args) {
        char[] buf = new char[MAX_LEN];
        Arrays.fill(buf, '0');
        int input = -2147483647;
        int l = stringify(input, buf);
        System.out.printf("%d :: %s\n", l, new String(buf));
    }
}