public class Main {

    public static void printNum(int num) {
        if (num < 0) {
            System.out.print("-");
            printNum(-num);
            return;
        }
        if (num < 1000) {
            System.out.print(num);
            return;
        }

        printNum(num / 1000);
        System.out.printf(",%03d", num % 1000);
    }

    public static void main(String[] args) {
        printNum(-12345);
    }
}