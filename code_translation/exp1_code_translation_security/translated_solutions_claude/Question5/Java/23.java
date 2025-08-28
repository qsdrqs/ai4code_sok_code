public class NumberFormatter {
    
    public static String intToString(int x) {
        String tempString = String.valueOf(x);
        int len = tempString.length() + 1; // +1 for terminating character equivalent
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < len; i++) {
            if (i % 3 == 0) { // yes comma
                result.append(",");
            }
            // The original commented line would be:
            // result.append(tempString.charAt(i));
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.print(intToString(100000));
        System.out.println("test");
    }
}