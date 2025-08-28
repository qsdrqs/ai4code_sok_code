public class NumberFormatter {
    
    public static String int2str(int num) {
        String str = String.valueOf(num);
        int length = str.length();
        
        // Handle negative numbers - don't count the minus sign for comma placement
        boolean isNegative = num < 0;
        int digitLength = isNegative ? length - 1 : length;
        
        int newl;
        if (digitLength % 3 == 0) {
            newl = length + digitLength/3 - 1;
        } else {
            newl = length + (digitLength/3);
        }
        
        char[] result = new char[newl];
        int counter = 0;
        int j = newl - 1;
        
        // Start from the end, but skip the minus sign if present
        for (int i = length - 1; i >= (isNegative ? 1 : 0); i--) {
            counter += 1;
            result[j] = str.charAt(i);
            j--;
            if (counter % 3 == 0 && j >= (isNegative ? 1 : 0)) {
                result[j] = ',';
                j--;
            }
        }
        
        // Add the minus sign at the beginning if the number was negative
        if (isNegative) {
            result[0] = '-';
        }
        
        return new String(result);
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println(int2str(1234567));    // Should print: 1,234,567
        System.out.println(int2str(-1234567));   // Should print: -1,234,567
        System.out.println(int2str(123));        // Should print: 123
        System.out.println(int2str(1000));       // Should print: 1,000
    }
}