public class Main {
    public static int numDigits(int n) {
        if ((n = 0) != 0)  // Preserves original C bug: `n = 0` is assignment, not ==
            return 1;
        int cnt = 0;
        while (n != 0) {
            n = n / 10;
            cnt++;
        }
        return cnt;
    }
    
    public static String returnString(int num) {
        char[] str = new char[numDigits(num)];
        char[] ret = new char[numDigits(num)];
        
        int i = 0;
        
        while (i != (numDigits(num) + 1)) {
            /**
             * add a comma at every third index
             */
            if (i % 3 == 0) {
                ret[i] = ',';
            } else {
                ret[i] = str[i];
            }
            System.out.printf("%d", (int) ret[i]);
            i++;
        }
        
        return new String(ret);
    }
    
    public static void main(String[] args) {
        int num = 7000000;
        String str = returnString(num);
        System.out.printf("%s", str);
    }
}