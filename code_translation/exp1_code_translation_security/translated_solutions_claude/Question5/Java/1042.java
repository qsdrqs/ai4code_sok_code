public class NumberFormatter {
    
    public static String intToString(int num) {
        char[] str = new char[100];
        int i = 0;
        int j = 0;
        int k = 0;
        int len = 0;
        int temp = num;
        int count = 0;
        
        // Calculate length
        while (temp != 0) {
            temp = temp / 10;
            len++;
        }
        
        temp = num;
        
        // Build string with digits and commas
        while (temp != 0) {
            str[i] = (char)(temp % 10 + '0');
            temp = temp / 10;
            i++;
            count++;
            if (count == 3 && temp != 0) {
                str[i] = ',';
                i++;
                count = 0;
            }
        }
        
        str[i] = '\0';
        i--;
        
        // Reverse the string
        while (j < i) {
            char tempChar = str[j];
            str[j] = str[i];
            str[i] = tempChar;
            j++;
            i--;
        }
        
        // Convert char array to String (up to null terminator)
        int strLen = 0;
        while (strLen < str.length && str[strLen] != '\0') {
            strLen++;
        }
        
        return new String(str, 0, strLen);
    }
    
    public static void main(String[] args) {
        System.out.println(intToString(7000000));
    }
}