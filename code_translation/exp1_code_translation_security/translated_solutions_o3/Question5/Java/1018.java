public class Main {

    /**
     * Inserts thousands separators (commas) into the decimal
     * representation of input and returns the resulting string.
     */
    public static String change(int input) {

        /* ----- count how many digits the number has ----- */
        int count = 0;
        int tmp   = input;
        do {
            tmp /= 10;
            ++count;
        } while (tmp != 0);

        /* total number of characters (digits + commas)     */
        int num = count + count / 3;

        System.out.println(num);          // same debug print as the C code

        /* we fill the character array from the end, exactly like in C */
        char[] result = new char[num];
        int    mid    = 0;                // counts digits inside one group

        for (int i = num - 1; i >= 0; ) {
            if (mid == 3) {               // time to insert a comma
                result[i--] = ',';
                mid = 0;
            } else {
                result[i--] = (char) ('0' + (input % 10));
                input /= 10;
                mid++;
            }
        }

        return new String(result);        // equivalent to C’s malloc’ed string
    }

    public static void main(String[] args) {
        String snum = change(700000000);
        System.out.println(snum);
    }
}