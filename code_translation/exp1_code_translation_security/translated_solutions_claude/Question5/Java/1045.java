public class NumberToString {
    
    public static String numberToString(int num) {
        StringBuilder strNum = new StringBuilder();
        int numSize = 0, i = 0, j = 0, commaCount = 0, temp = 0;
        
        temp = num;
        if (num < 0) {
            temp *= -1;
        }
        
        // Calculate number of digits
        while (temp > 0) {
            temp /= 10;
            numSize++;
        }
        
        commaCount = (numSize - 1) / 3;
        
        // Handle negative sign
        if (num < 0) {
            strNum.append('-');
            num *= -1;
            i++;
        }
        
        // Build the string with commas
        for (j = numSize - 1; j >= 0; j--) {
            strNum.append((char)((num / power(10, j)) + '0'));
            num %= power(10, j);
            i++;
            if ((j % 3 == 0) && (j != 0)) {
                strNum.append(',');
                i++;
            }
        }
        
        return strNum.toString();
    }
    
    public static int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }
    
    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum);
    }
}