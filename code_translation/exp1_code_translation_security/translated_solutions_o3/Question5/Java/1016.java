/*
 * Write a function in Java that takes an integer and converts it to a String.
 * The output format is identical to the given C version:
 *     – the least–significant group contains 4 digits,
 *     – every following group (towards the most-significant side) contains 3 digits,
 *     – groups are separated by commas,
 *     – a minus sign is emitted for negative numbers.
 */

public class Main {

    public static String intToString(int num) {
        int len  = 0;     // final length of the resulting string
        int temp = Math.abs(num);
        int counter = 0;  // counts digits within the current group

        /* ---------------- Calculate resulting length ---------------- */
        if (temp == 0) {                    // special case “0”
            len = 1;
        } else {
            while (temp != 0) {
                temp   /= 10;               // drop one digit
                len++;                      // one more digit in the output
                counter++;                  // one more digit in this group

                // after the first 4 digits insert a comma,
                // afterwards after every 3 digits
                if (counter == 4) {
                    len++;                  // account for the comma
                    counter = 1;            // reset counter (already have one digit in new group)
                }
            }
        }

        boolean negative = num < 0;
        int stop = 0;                       // index where digit filling may stop
        if (negative) {
            len++;          // space for the minus sign
            stop = 1;       // leave index 0 free for the minus sign
        }

        /* ---------------- Fill the character buffer ---------------- */
        char[] str = new char[len];
        if (negative) {
            str[0] = '-';
        }

        int n = Math.abs(num);              // work with a positive value
        int count = 0;                      // digits placed since last comma

        for (int i = len - 1; i >= stop; i--) {
            count++;
            if (count == 4) {               // time for a comma
                str[i] = ',';
                count  = 0;
                continue;                   // stay at the same 'i' position next loop
            }
            str[i] = (char) ((n % 10) + '0');
            n /= 10;
        }
        return new String(str);
    }

    public static void main(String[] args) {
        int num = -10099870;
        String str = intToString(num);
        System.out.println(str);            // -> -10,099,870
    }
}