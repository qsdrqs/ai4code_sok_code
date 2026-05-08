public class Main {
    
    public static String convert(int num) {
        String buf = "";
        buf = String.format("%d", num);
        System.out.print(buf);
        return buf;
    }
    
    public static void main(String[] args) {
        System.out.print(convert(100));
    }
}