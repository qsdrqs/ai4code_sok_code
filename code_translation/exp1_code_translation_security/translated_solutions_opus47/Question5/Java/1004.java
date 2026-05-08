import java.util.Arrays;

public class Main {
    static final int MAX_DIGITS = 100;

    /*
    Function repr

    Takes in a signed integer and returns the string representation of that integer.
    */
    public static String repr(int n) {
        char[] str = new char[MAX_DIGITS];
        int i = 0;
        int sign = 1;
        if (n == -2147483648) {
            // When multiplied by -1 this overflows, so handle it separately.
            return "-2,147,483,648";
        }
        if (n == 0) {
            return "0";
        }
        if (n < 0) {
            sign = -1;
            n *= -1;
        }
        while (n > 0) {
            str[i++] = (char) ((n % 10) + '0');
            if ((i + 1) % 4 == 0 && n > 10) {
                str[i++] = ',';
            }
            n /= 10;
        }
        if (sign == -1) {
            str[i++] = '-';
        }
        char[] result = Arrays.copyOf(str, i);
        reverse(result);
        return new String(result);
    }

    /*
    Function reverse

    Takes in a string and reverses it.
    */
    public static void reverse(char[] str) {
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int n = 0;
        String str = repr(n);
        System.out.print(str);
    }
}