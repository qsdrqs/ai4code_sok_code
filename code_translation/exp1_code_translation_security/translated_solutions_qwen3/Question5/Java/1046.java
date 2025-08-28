public class Main {
    public static char convert(int num) {
        String buf = Integer.toString(num);
        System.out.print(buf);
        return buf.charAt(0);
    }

    public static void main(String[] args) {
        System.out.printf("%c", convert(100));
    }
}