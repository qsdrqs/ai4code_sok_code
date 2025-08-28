public class NumberFormatter {
    
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
        
        String s = String.valueOf(value);
        int size = digits + numCommas + 1;
        char[] dest = new char[size];
        
        int index = size - 2;
        while (digits > 0) {
            dest[index--] = s.charAt(digits - 1);
            digits--;
            if (digits % 3 == 0 && digits > 0) {
                dest[index--] = ',';
            }
        }
        
        String result = new String(dest, 0, size - 1);
        
        if (isNegative) {
            result = "-" + result;
        }
        
        System.out.print(result);
        return result;
    }
    
    public static void main(String[] args) {
        intToStringWithCommas(7000000);
    }
}