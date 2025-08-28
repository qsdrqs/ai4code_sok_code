public class IntToString {

    public static void main(String[] args) {
        int num = -10099870;
        String str = intToString(num);
        System.out.println(str);  // Expected output: "-10,099,870"
    }

    public static String intToString(int num) {
        int i = 0;
        int temp = num;
        int len = 0;
        int counter = 0;

        // Calculate the length of the string including commas
        while (temp != 0) {
            temp = temp / 10;
            len++;
            counter++;
            if (counter == 4) {
                len++;  // Add a comma after every 4 digits
                counter = 1;
            }
        }

        // Allocate the character array
        char[] str = new char[len + 1];  // +1 for possible minus sign or null terminator

        int stop = 0;
        if (num < 0) {
            len++;  // Account for the minus sign
            str[0] = '-';
            stop = 1;
        }

        int count = 0;
        for (i = len - 1; i >= stop; i--) {
            count++;
            if (count == 4) {
                str[i] = ',';
                count = 0;
            } else {
                // Extract the last digit and convert to char
                str[i] = (char) ((num % 10) + '0');
                num = num / 10;
            }
        }

        // Return the constructed string
        return new String(str, 0, len);
    }
}