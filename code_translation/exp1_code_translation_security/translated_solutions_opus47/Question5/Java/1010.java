public class Main {
    public static void main(String[] args) {
        System.out.printf("   %s\n", stringIt(-123457));
    }
    
    // Return a string containing the expansion of the signed int
    public static String stringIt(int value) {
        // Java StringBuilder grows dynamically, so no need to preallocate 30 chars.
        StringBuilder returned = new StringBuilder();
        if (value < 0) {
            returned.append('-');
            value = -value;
        }
        // Equivalent to sprintf(str, "%d", value) - build the digit portion separately
        StringBuilder str = new StringBuilder(String.valueOf(value));
        int length = str.length();
        int commaCount = (length - 1) / 3;
        int end = length;
        while (commaCount > 0) {
            int start = end - 3;
            // Equivalent to printf("%s\n", start) - prints substring starting at 'start'
            System.out.println(str.substring(start));
            // Equivalent to shifting characters right and inserting ','
            str.insert(start, ',');
            commaCount--;
            end = start;
        }
        returned.append(str);
        return returned.toString();
    }
}