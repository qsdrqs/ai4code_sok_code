public class IntegerRepresentation {
    private static final int MAX_DIGITS = 100;
    
    /*
    Function repr
    
    Takes in a signed integer and returns the string representation of that integer.
    */
    public static String repr(int n) {
        StringBuilder str = new StringBuilder(MAX_DIGITS);
        int sign = 1;
        
        if (n == -2147483648) {
            // When multiplied by -1 this overflows, so handle it separately.
            return "-2,147,483,648";
        }
        
        if (n == 0) {
            return "0";
        }
        
        if (n < 0) {
            sign = -1;
            n *= -1;
        }
        
        while (n > 0) {
            str.append((char)((n % 10) + '0'));
            if ((str.length() + 1) % 4 == 0 && n > 10) {
                str.append(',');
            }
            n /= 10;
        }
        
        if (sign == -1) {
            str.append('-');
        }
        
        return reverse(str.toString());
    }
    
    /*
    Function reverse
    
    Takes in a string and reverses it.
    */
    public static String reverse(String str) {
        char[] charArray = str.toCharArray();
        int i = 0;
        int j = charArray.length - 1;
        
        while (i < j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
        
        return new String(charArray);
    }
    
    public static void main(String[] args) {
        int n = 0;
        String str = repr(n);
        System.out.print(str);
    }
}