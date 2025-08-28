import java.util.*;

public class Main {

    public static String change(int input) {
        int count = 0;
        int tmp = input;

        // Calculate the number of digits in the input
        do {
            tmp /= 10;
            ++count;
        } while (tmp != 0);

        // Calculate the size of the resulting string (including commas)
        int num = count + count / 3;
        char[] result = new char[num];
        int mid = 0;

        // Fill the result array from the end
        for (int i = num - 1; i >= 0; i--) {
            if (mid == 3) {
                mid = 0;
                result[i] = ',';
                i--;
            }
            result[i] = (char) (input % 10 + '0');
            input /= 10;
            mid += 1;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String snum = change(700000000);
        System.out.println(snum);
    }
}