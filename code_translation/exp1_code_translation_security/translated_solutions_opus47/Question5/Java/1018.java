public class Main {
    public static char[] change(int input) {
        int count = 0;
        int tmp = input;
        do {
            tmp /= 10;
            ++count;
        } while (tmp != 0);

        int num = count + count / 3;
        char[] result = new char[num + 2];
        int mid = 0;
        System.out.println(num);
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
        return result;
    }

    public static void main(String[] args) {
        char[] snum = change(700000000);
        System.out.println(new String(snum));
    }
}