public class IntegerFormatter {
    
    // Helper method to reverse a StringBuilder
    private static void reverse(StringBuilder s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char temp = s.charAt(i);
            s.setCharAt(i, s.charAt(j));
            s.setCharAt(j, temp);
            i++;
            j--;
        }
    }
    
    // Convert integer to string (equivalent to itoa)
    private static void itoa(int n, StringBuilder s) {
        int sign;
        if ((sign = n) < 0) { // record sign
            n = -n; // make n positive
        }
        
        do { // generate digits in reverse order
            s.append((char)(n % 10 + '0')); // get next digit
        } while ((n /= 10) > 0); // delete it
        
        if (sign < 0) {
            s.append('-');
        }
    }
    
    // Insert comma at specified position
    private static void insertComma(StringBuilder str, int pos) {
        str.insert(pos, ',');
    }
    
    // Main function to convert integer to formatted string
    public static String intToString(int num) {
        StringBuilder s = new StringBuilder();
        itoa(num, s);
        
        int length = s.length();
        
        for (int i = 3; i <= length; i += 4) {
            insertComma(s, i);
        }
        
        // Remove trailing comma if it exists
        if (s.length() > 0 && s.charAt(s.length() - 1) == ',') {
            s.deleteCharAt(s.length() - 1);
        }
        
        reverse(s);
        
        return s.toString();
    }
    
    public static void main(String[] args) {
        int num = 7000;
        String result = intToString(num);
        System.out.print(result);
    }
}