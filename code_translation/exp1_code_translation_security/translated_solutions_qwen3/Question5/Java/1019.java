public class Main {
    public static void main(String[] args) {
        int leet = 1337;
        String leetStr = stringRep(leet);
        System.out.println(leetStr);
    }

    public static String stringRep(int num) {
        return Integer.toString(num);
    }
}