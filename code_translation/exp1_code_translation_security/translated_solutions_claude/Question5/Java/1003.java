public class StringConverter {
    
    // Using Integer.toString()
    public static String int2str(int num) {
        return Integer.toString(num);
    }
    
    // Or using StringBuilder (for consistency with buffer approach)
    public static String int2str(int num) {
        StringBuilder buf = new StringBuilder();
        buf.append(num);
        return buf.toString();
    }
}