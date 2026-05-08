public class Main {
    public static int numDigits(int n) {
        int digits = 0;
        
        while (n != 0) {
            n /= 10;
            digits++;
        }
        
        return digits;
    }

    public static String intToStringWithCommas(int value) {
        boolean isNegative = value < 0;
        if (isNegative) {
            value *= -1;
        }
        int digits = numDigits(value);
        int numCommas = digits / 3;
        if (digits % 3 == 0) {
            numCommas -= 1;
        }
        char[] s = new char[digits];
        int size = digits + numCommas + 1;
        char[] dest = new char[size];
        
        // Java equivalent of sprintf(s, "%d", value)
        String valueStr = String.valueOf(value);
        for (int i = 0; i < digits; i++) {
            s[i] = valueStr.charAt(i);
        }
        
        int index = size - 2;
        while (digits > 0) {
            dest[index--] = s[digits - 1];
            if (digits % 3 == 0 && digits > 0) {
                dest[index--] = ',';
            }
            digits--;
        }
        dest[size - 1] = '\0';
        
        String result = new String(dest, 0, size - 1);
        System.out.print(result);
        return result;
    }

    public static void main(String[] args) {
        intToStringWithCommas(7000000);
    }
}