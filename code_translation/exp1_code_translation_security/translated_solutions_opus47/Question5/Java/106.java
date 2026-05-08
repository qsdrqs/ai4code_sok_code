public class Main {
    private static final int MAX_STR_LEN = 128;
    
    public static String reverseStr(String str) {
        int len = str.length();
        char[] newStr = new char[len];
        for (int i = 0; i < len; i++) {
            newStr[i] = str.charAt(len - i - 1);
        }
        return new String(newStr);
    }
    
    /*
    Return a string representation of a signed integer
    
    Args:
        int num: input number as a signed integer
        
    Return:
        string representation of the signed integer
    */
    public static String strOfInt(int num) {
        char[] str = new char[MAX_STR_LEN];
        int i = 0;
        int isNeg = 0;
        if (num < 0) {
            isNeg = 1;
            num = -num;
        }
        do {
            str[i++] = (char)(num % 10 + '0');
            num /= 10;
        } while (num != 0);
        if (isNeg == 1) {
            str[i++] = '-';
        }
        return reverseStr(new String(str, 0, i));
    }
    
    public static void main(String[] args) {
        System.out.print(strOfInt(7000000));
        System.out.println();
    }
}