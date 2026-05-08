public class Main {
    
    public static void numToStr(int num, char[] str) {
        // Equivalent of sprintf(str, "%d", num);
        String formatted = String.valueOf(num);
        for (int i = 0; i < formatted.length(); i++) {
            str[i] = formatted.charAt(i);
        }
        str[formatted.length()] = '\0';
        
        // Equivalent of strlen(str);
        int length = formatted.length();
        int numCommas = 0;
        
        if (num == 0) {
            return;
        }
        else if (num > 0) {
            numCommas = (length - 1) / 3;
            int digitsMoved = 0;
            
            for (int i = length; i > 0; i--) {
                if (digitsMoved < 3) {
                    str[i] = str[i - 1];
                    digitsMoved++;
                }
                else {
                    str[i] = ',';
                    digitsMoved = 0;
                }
            }
        }
        else {
            numCommas = (length - 2) / 3;
            int digitsMoved = 0;
            
            for (int i = length; i > 1; i--) {
                if (digitsMoved < 3) {
                    str[i] = str[i - 1];
                    digitsMoved++;
                }
                else {
                    str[i] = ',';
                    digitsMoved = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        //char[] str = numToStr(7000);
        int num = 7042;
        char[] str = new char[24];
        
        numToStr(num, str);
        
        // Emulate printf("Output: %s", str); - print up to the first '\0' (null terminator)
        int end = 0;
        while (end < str.length && str[end] != '\0') end++;
        System.out.printf("Output: %s", new String(str, 0, end));
        
        //System.out.print("Hi");
    }
}