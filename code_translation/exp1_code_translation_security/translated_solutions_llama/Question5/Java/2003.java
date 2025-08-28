public class Main {

    private static final int MAX_LEN = 16;

    /**
     * Converts an integer into a string with commas as thousand separators.
     *
     * @param num The number to convert.
     * @return A string representation of the number with commas.
     */
    public static String numToString(int num) {
        if (num == 0) {
            return "0";
        }

        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }

        int totalDigits = String.valueOf(num).length();
        int numCommas = (totalDigits - 1) / 3;
        int strLen = totalDigits + numCommas;
        if (isNeg) {
            strLen += 1;
        }

        StringBuilder sb = new StringBuilder(strLen);
        if (isNeg) {
            sb.append('-');
        }

        String numStr = String.valueOf(num);
        int index = numStr.length() - 1;
        int count = 0;
        while (index >= 0) {
            sb.insert(0, numStr.charAt(index));
            count++;
            index--;
            if (count % 3 == 0 && index >= 0) {
                sb.insert(0, ',');
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(numToString(-5305000));
    }
}