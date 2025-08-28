public class StringFormatter {
    
    public static void main(String[] args) {
        System.out.println("   " + stringIt(-123457));
    }
    
    // Return a string containing the expansion of the signed int with comma separators
    public static String stringIt(int value) {
        // Handle the sign
        boolean isNegative = false;
        if (value < 0) {
            isNegative = true;
            value = -value;
        }
        
        // Convert to string
        String str = String.valueOf(value);
        int length = str.length();
        int commaCount = (length - 1) / 3;
        
        // If no commas needed, just return with sign if necessary
        if (commaCount == 0) {
            return isNegative ? "-" + str : str;
        }
        
        // Use StringBuilder for efficient string manipulation
        StringBuilder result = new StringBuilder(str);
        
        // Add commas from right to left
        while (commaCount > 0) {
            int insertPos = result.length() - (commaCount * 3);
            result.insert(insertPos, ',');
            commaCount--;
        }
        
        // Add negative sign if needed
        if (isNegative) {
            result.insert(0, '-');
        }
        
        return result.toString();
    }
}