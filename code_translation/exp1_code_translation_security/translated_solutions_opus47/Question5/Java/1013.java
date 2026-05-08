public class Main {
    
    public static String convert(int num) {
        // Did not add comma because it's too complicated
        // In Java, we don't need to manually manage memory or buffer sizes
        return String.valueOf(num);
    }
    
    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
        // No need to free memory in Java - garbage collector handles it
    }
}