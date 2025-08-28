public class Main {

    public static String int_to_string(int input) {
        String buffer = String.valueOf(input);
        System.out.println("you have entered: " + buffer);
        return null;
    }

    public static void main(String[] args) {
        int_to_string(7000000);
    }
}