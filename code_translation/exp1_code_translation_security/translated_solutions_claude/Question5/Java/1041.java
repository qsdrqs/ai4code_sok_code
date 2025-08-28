public class NumberFormatter {
    
    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str);
    }
    
    public static String reverseStr(String str) {
        char[] charArray = str.toCharArray();
        int i, j;
        char temp;
        
        for (i = 0, j = charArray.length - 1; i < j; i++, j--) {
            temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }
        
        return new String(charArray);
    }
    
    public static String addCommas(int num) {
        StringBuilder str = new StringBuilder();
        int count = 0;
        
        do {
            str.append((char)(num % 10 + '0'));
            num /= 10;
            count++;
            
            if (count == 3 && num != 0) {
                str.append(',');
                count = 0;
            }
        } while (num != 0);
        
        return reverseStr(str.toString());
    }
}