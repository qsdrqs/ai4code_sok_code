public class Main {
    public static final int MAX_LEN = 16;
    
    public static String numToString(int num) {
        if (num == 0) { return "0"; }
        
        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }
        
        int curNum = num;
        int totalDigits = 0;
        while (curNum > 0) {
            curNum = curNum / 10;
            totalDigits++;
        }
        int numCommas = (totalDigits - 1) / 3;
        int strLen = totalDigits + numCommas;
        if (isNeg) {
            strLen += 1;
        }
        
        char[] str = new char[strLen];
        
        curNum = num;
        int index = 0;
        int digits = 0;
        while (curNum > 0) {
            char curChar = (char)((curNum % 10) + 48);
            str[strLen - index - 1] = curChar;
            curNum = curNum / 10;
            index++;
            digits++;
            if (digits % 3 == 0) {
                str[strLen - index - 1] = ',';
                index++;
            }
        }
        
        if (isNeg) {
            str[0] = '-';
        }
        
        return new String(str);
    }
    
    public static void main(String[] args) {
        System.out.print(numToString(-5305000));
    }
}