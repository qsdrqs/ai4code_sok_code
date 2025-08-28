public class Main {
    
    public static String convert(int num) {
        // Did not add comma because it's too complicated
        
        // In Java, String.valueOf() handles the conversion and memory allocation automatically
        String str = String.valueOf(num);
        
        // No need for manual memory management in Java
        // The original C code had some issues with buffer size, but Java handles this automatically
        
        return str;
    }
    
    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
        // No need to free memory in Java - garbage collector handles it
    }
}