public class Main {
    // Converts an integer to its string representation
    public static String convert(int num) {
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        String ans = convert(700000);
        System.out.println(ans);
    }
}