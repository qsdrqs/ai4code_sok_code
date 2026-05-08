public class Main {
    
    // Reverses the string (equivalent to C's reverse function)
    public static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    // Converts int n to a string with digits in reverse order (equivalent to C's itoa)
    public static String itoa(int n) {
        int sign = n;
        if (sign < 0) {
            n = -n;  // make n positive
        }
        StringBuilder sb = new StringBuilder();
        do {  // generate digits in reverse order
            sb.append((char)(n % 10 + '0'));  // get next digit
            n /= 10;  // delete it
        } while (n > 0);
        if (sign < 0) {
            sb.append('-');
        }
        return sb.toString();
    }
    
    // Inserts a comma at the given position in the string
    public static String insertComma(String str, int pos) {
        return str.substring(0, pos) + "," + str.substring(pos);
    }
    
    // Main conversion function
    public static String intToString(int num) {
        String s = itoa(num);
        int length = s.length();
        for (int i = 3; i <= length; i += 4) {
            s = insertComma(s, i);
        }
        // Remove trailing comma if present
        if (s.charAt(s.length() - 1) == ',') {
            s = s.substring(0, s.length() - 1);
        }
        s = reverse(s);
        return s;
    }
    
    public static void main(String[] args) {
        int num = 7000;
        String result = intToString(num);
        System.out.print(result);
    }
}