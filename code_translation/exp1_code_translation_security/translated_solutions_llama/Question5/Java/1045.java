public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     *
     * @param num The number to be converted.
     * @return A string representation of the number with commas.
     */
    public static String numberToString(int num) {
        StringBuilder strNum = new StringBuilder();
        boolean isNegative = false;

        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        int numSize = String.valueOf(num).length();
        int commaCount = (numSize - 1) / 3;

        if (isNegative) {
            strNum.append('-');
        }

        String numStr = String.valueOf(num);
        for (int i = numStr.length() - 1; i >= 0; i--) {
            strNum.append(numStr.charAt(i));
            if ((numStr.length() - i) % 3 == 0 && i != 0) {
                strNum.append(',');
            }
        }

        // Reverse the string to get the correct order
        return strNum.reverse().toString();
    }

    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum);
    }
}