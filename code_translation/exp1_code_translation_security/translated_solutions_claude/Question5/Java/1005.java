public class StringifyNumber {
    private static final int MAX_LEN = 14;
    
    public static int stringify(int i, char[] buf) {
        char[] tmp = "00000000000000".toCharArray();
        int j = (i >= 0) ? i : -1 * i;
        int k = MAX_LEN - 1;
        int c = 0;
        
        while (j > 0 && k >= 0) {
            if (c != 0 && c % 3 == 0) {
                tmp[k] = ',';
                k--;
                c = 0;
            }
            // Insert comma
            int len_written = MAX_LEN - 1 - k;
            // Convert digit to char
            int digit = j % 10;
            char d_char = (char)(digit + '0');
            // Add to string
            tmp[k] = d_char;
            // Move to next digit
            j /= 10;
            k--;
            c++;
        }
        
        if (i < 0) {
            tmp[k] = '-';
            k--;
        }
        
        for (int n = k; n < MAX_LEN; n++) {
            buf[n - k] = tmp[n + 1];
        }
        
        return MAX_LEN - 1 - k;
    }
    
    public static void main(String[] args) {
        char[] buf = "00000000000000".toCharArray();
        int input = -2147483647;
        int l = stringify(input, buf);
        System.out.printf("%d :: %s%n", l, new String(buf, 0, l));
    }
}