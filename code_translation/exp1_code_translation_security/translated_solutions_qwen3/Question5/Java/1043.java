public class Main {
    public static String numToString(int num) {
        return String.format("%d", num);
    }

    public static void main(String[] args) {
        System.out.printf("%s", numToString(7000000));
    }
}