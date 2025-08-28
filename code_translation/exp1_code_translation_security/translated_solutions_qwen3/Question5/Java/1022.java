public class Main {
    public static void main(String[] args) {
        print_num(-12345);
    }

    public static void print_num(int num) {
        if (num < 0) {
            System.out.print("-");
            print_num(-num);
            return;
        }
        if (num < 1000) {
            System.out.print(num);
            return;
        }
        print_num(num / 1000);
        System.out.printf(",%03d", num % 1000);
    }
}