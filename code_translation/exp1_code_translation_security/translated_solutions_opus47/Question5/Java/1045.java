public class Main {
    public static String numberToString(int num) {
        int numSize = 0;
        int i = 0, j = 0;
        int commaCount = 0;
        int temp = num;
        
        if (num < 0) {
            temp *= -1;
        }
        while (temp > 0) {
            temp /= 10;
            numSize++;
        }
        commaCount = (numSize - 1) / 3;
        // In Java we size the array exactly (no null terminator needed).
        // We add +1 if negative to accommodate the '-' sign.
        int totalSize = numSize + commaCount + (num < 0 ? 1 : 0);
        char[] strNum = new char[totalSize];
        
        if (num < 0) {
            strNum[0] = '-';
            num *= -1;
            i++;
        }
        for (j = numSize - 1; j >= 0; j--) {
            strNum[i] = (char)((num / power(10, j)) + '0');
            num %= power(10, j);
            i++;
            if ((j % 3 == 0) && (j != 0)) {
                strNum[i] = ',';
                i++;
            }
        }
        return new String(strNum);
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