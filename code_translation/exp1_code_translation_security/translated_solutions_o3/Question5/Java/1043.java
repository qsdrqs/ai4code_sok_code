public class Main {

    // Converts an int to its String representation.
    public static String numToString(int num) {
        return Integer.toString(num);     // same as String.valueOf(num)
    }

    public static void main(String[] args) {
        System.out.print(numToString(7_000_000));
    }
}