public class NumberFormatter {
    
    public static String numToStr(int num) {
        String str = String.valueOf(num);
        char[] charArray = new char[str.length() + 10]; // Extra space for commas
        
        // Copy string to char array
        for (int i = 0; i < str.length(); i++) {
            charArray[i] = str.charAt(i);
        }
        
        int length = str.length();
        int numCommas = 0;
        
        if (num == 0) {
            return str;
        }
        else if (num > 0) {
            numCommas = (length - 1) / 3;
            int digitsMoved = 0;
            
            for (int i = length; i > 0; i--) {
                if (digitsMoved < 3) {
                    charArray[i] = charArray[i - 1];
                    digitsMoved++;
                }
                else {
                    charArray[i] = ',';
                    digitsMoved = 0;
                }
            }
        }
        else {
            numCommas = (length - 2) / 3;
            int digitsMoved = 0;
            
            for (int i = length; i > 1; i--) {
                if (digitsMoved < 3) {
                    charArray[i] = charArray[i - 1];
                    digitsMoved++;
                }
                else {
                    charArray[i] = ',';
                    digitsMoved = 0;
                }
            }
        }
        
        // Convert char array back to string, accounting for added commas
        return new String(charArray, 0, length + numCommas);
    }
    
    public static void main(String[] args) {
        int num = 7042;
        String str = numToStr(num);
        
        System.out.printf("Output: %s", str);
    }
}