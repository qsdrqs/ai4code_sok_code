public class Main {

    public static String change(int input) {
        int count = 0;
        int tmp = input;
        do {
            tmp /= 10;
            ++count;
        } while (tmp != 0);

        int num = count + (count / 3);
        char[] result = new char[num];
        int mid = 0;
        int i = num - 1;

        while (i >= 0) {
            if (mid == 3) {
                mid = 0;
                result[i] = ',';
                i--;
            }

            int digit = input % 10;
            result[i] = (char) ('0' + digit);
            input /= 10;
            mid++;
            i--;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String snum = change(700000000);
        System.out.println(snum);
    }
}