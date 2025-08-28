public class Main {

    public static String convert(long value) {
        return String.format("%,d", value);
    }

    public static void main(String[] args) {
        String test = convert(1000);
        System.out.println(test);
    }
}