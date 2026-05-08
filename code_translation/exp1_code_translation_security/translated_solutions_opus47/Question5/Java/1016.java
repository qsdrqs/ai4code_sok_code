public class Main {
    public static String intToString(int num) {
        int i = 0;
        int j = 0;
        int temp = 0;
        int len = 0;
        char[] str = null;

        temp = num;
        int counter = 0;
        while (temp != 0) {
            temp = temp / 10;
            len++;
            counter++;
            // theres 1 comma for first 4 digits, then 1 for every 3 after
            if (counter == 4) {
                len++;
                counter = 1;
            }
        }
        str = new char[len + 1];
        // In Java, 'new' throws OutOfMemoryError on failure rather than returning null

        int stop = 0;
        if (num < 0) {
            len++; // +1 for the minus sign character
            stop = 1; // add in the minus sign at start of string
            str[0] = '-';
        }
        int count = 0;
        for (i = len - 1; i >= stop; i--) {
            count++;
            if (count == 4) {
                str[i] = ',';
                count = 0;
                continue;
            }
            str[i] = (char) ((num % 10) + '0');
            num = num / 10;
        }
        // Java Strings don't require null termination
        return new String(str, 0, len);
    }

    public static void main(String[] args) {
        int num = 0;
        String str = null;

        num = -10099870;
        str = intToString(num);
        System.out.println(str);
        // No free() needed in Java; garbage collection handles memory
    }
}