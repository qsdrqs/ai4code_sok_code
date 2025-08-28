public class Main {
    
    public static String convert(int num) {
        String buf = "";
        buf = String.valueOf(num);
        System.out.print(buf);
        return buf;
    }
    
    public static void main(String[] args) {
        System.out.printf("%s", convert(100));
    }
}