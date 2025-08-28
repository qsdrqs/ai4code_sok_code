public class Main {

    public static void main(String[] args) {
        int num = 7000000;
        String str = returnString(num);
        System.out.print(str);
    }

    /**
     * Returns the number of digits in the given integer.
     */
    public static int numDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int cnt = 0;
        while (n != 0) {
            n = n / 10;
            cnt++;
        }
        return cnt;
    }

    /**
     * Returns a string representation of the number with commas inserted
     * every three digits from the right.
     */
    public static String returnString(int num) {
        int len = numDigits(num);
        String numStr = Integer.toString(num);
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < len; i++) {
            if (i > 0 && (len - i) % 3 == 0) {
                ret.append(',');
            }
            ret.append(numStr.charAt(i));
        }

        return ret.toString();
    }
}