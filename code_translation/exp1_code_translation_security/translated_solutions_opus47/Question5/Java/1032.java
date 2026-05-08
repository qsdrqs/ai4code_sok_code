public class Main {
    
    public static String intToString(int num) {
        int len = 0;
        int temp = num;
        while (temp != 0) {
            temp /= 10;
            len++;
        }
        char[] str = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            str[i] = (char)((num % 10) + '0');
            num /= 10;
        }
        return new String(str);
    }
    
    public static String intToStringWithCommas(int num) {
        String str = intToString(num);
        int len = str.length();
        int commaCount = (len - 1) / 3;
        char[] newStr = new char[len + commaCount];
        
        int j = 0;
        int k = 0;
        for (int i = 0; i < len + commaCount; i++) {
            if ((len - j) % 3 == 0 && k != 1 && i != 0) {
                newStr[i] = ',';
                k = 1;
            } else {
                newStr[i] = str.charAt(j);
                j++;
                if (k == 1) {
                    k = 0;
                }
            }
        }
        return new String(newStr);
    }
    
    public static void main(String[] args) {
        int num = 7654321;
        String str = intToStringWithCommas(num);
        System.out.println(str);
    }
}