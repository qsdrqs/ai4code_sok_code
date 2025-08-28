public class IntToString {
    
    public static String intToString(int num) {
        int i = 0;
        int j = 0;
        int temp = 0;
        int len = 0;
        
        temp = num;
        int counter = 0;
        while (temp != 0) {
            temp = temp / 10;
            len++;
            counter++;
            // there's 1 comma for first 4 digits, then 1 for every 3 after
            if (counter == 4) {
                len++;
                counter = 1;
            }
        }
        
        char[] str = new char[len + 1];
        
        int stop = 0;
        if (num < 0) {
            len++; // +1 for the minus sign character
            stop = 1; // add in the minus sign at start of string
            str[0] = '-';
            // Resize array to accommodate the minus sign
            char[] newStr = new char[len + 1];
            System.arraycopy(str, 0, newStr, 0, str.length);
            str = newStr;
        }
        
        int count = 0;
        for (i = len - 1; i >= stop; i--) {
            count++;
            if (count == 4) {
                str[i] = ',';
                count = 0;
                continue;
            }
            str[i] = (char)((num % 10) + '0');
            num = num / 10;
        }
        
        // Convert char array to string, excluding the null terminator position
        return new String(str, 0, len);
    }
    
    public static void main(String[] args) {
        int num = 0;
        String str = null;
        
        num = -10099870;
        str = intToString(num);
        System.out.println(str);
    }
}