public class IntegerToString {
    
    /* 
    A function that reverse a string
    */
    public static String strrev(String input) {
        char[] chars = input.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        char temp;
        
        while (end > start) {
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        
        return new String(chars);
    }
    
    /* 
    A function that takes a signed integer num and returns a string representation of the integer
    */
    public static String integerToString(int num) {
        StringBuilder result = new StringBuilder();
        boolean isNegative = false;
        
        if (num < 0) {
            isNegative = true;
            num = -num;
        }
        
        do {
            int digit = num % 10;
            result.append((char)('0' + digit));
            num /= 10;
        } while (num != 0);
        
        if (isNegative) {
            result.append('-');
        }
        
        return strrev(result.toString());
    }
    
    public static void main(String[] args) {
        String num = integerToString(12345);
        System.out.println(num);
        
        num = integerToString(-12345);
        System.out.println(num);
    }
}