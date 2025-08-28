public class Main {

    // Function to calculate the number of digits in an integer
    public static int numDigits(int n) {
        int digits = 0;

        while (n != 0) {
            n /= 10;
            digits++;
        }

        return digits;
    }

    // Function to convert an integer to a string with commas
    public static String intToStringWithCommas(int value) {
        boolean isNegative = value < 0;
        if (isNegative) {
            value *= -1;
        }

        int digits = numDigits(value);
        int numCommas = digits / 3;
        if (digits % 3 == 0) {
            numCommas -= 1;
        }

        // Convert the integer to a string
        String s = Integer.toString(value);
        int size = digits + numCommas;
        char[] dest = new char[size];

        int index = size - 1;
        int sIndex = s.length() - 1;

        while (sIndex >= 0) {
            dest[index--] = s.charAt(sIndex--);
            if ((s.length() - sIndex - 1) % 3 == 0 && sIndex >= 0) {
                dest[index--] = ',';
            }
        }

        String result = new String(dest);
        if (isNegative) {
            result = "-" + result;
        }

        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        intToStringWithCommas(7000000);
    }
}