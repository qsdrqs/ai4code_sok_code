public class StringConverter {
    private static final int MAX_STR_LEN = 128;
    
    public static String reverseStr(String str) {
        int len = str.length();
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < len; i++) {
            newStr.append(str.charAt(len - i - 1));
        }
        return newStr.toString();
    }
    
    /**
     * Return a string representation of a signed integer
     * 
     * @param num input number as a signed integer
     * @return string representation of the signed integer
     */
    public static String strOfInt(int num) {
        StringBuilder str = new StringBuilder();
        boolean isNeg = false;
        
        if (num < 0) {
            isNeg = true;
            num = -num;
        }
        
        do {
            str.append((char)(num % 10 + '0'));
            num /= 10;
        } while (num != 0);
        
        if (isNeg) {
            str.append('-');
        }
        
        return reverseStr(str.toString());
    }
    
    public static void main(String[] args) {
        System.out.print(strOfInt(7000000));
        System.out.println();
    }
}