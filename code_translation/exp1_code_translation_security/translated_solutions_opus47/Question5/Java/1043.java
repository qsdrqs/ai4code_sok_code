public class Main {
    public static String numToString(int num) {
        String stringNum = String.format("%d", num);
        String ans = stringNum;
        return ans;
    }

    public static void main(String[] args) {
        System.out.printf("%s", numToString(7000000));
    }
}