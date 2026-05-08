public class Main {
    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str);
    }

    public static char[] reverseStr(char[] str) {
        int i, j;
        char temp;
        for (i = 0, j = str.length - 1; i < j; i++, j--) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
        return str;
    }

    public static String addCommas(int num) {
        // Max size needed: 10 digits + 3 commas = 13 chars (rounded up to 16)
        char[] buffer = new char[16];
        int i, count = 0;
        for (i = 0; num != 0; i++) {
            buffer[i] = (char) (num % 10 + '0');
            num /= 10;
            count++;
            if (count == 3 && num != 0) {
                buffer[i + 1] = ',';
                count = 0;
                i++;
            }
        }
        // Copy the used portion into a result array (Java has no null terminator)
        char[] result = new char[i];
        System.arraycopy(buffer, 0, result, 0, i);
        reverseStr(result);
        return new String(result);
    }
}