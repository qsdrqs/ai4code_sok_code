public class Main {

    /**
     * Converts an integer to a string with commas as thousand separators.
     *
     * @param num the integer to convert
     * @return the string representation of the integer
     */
    public static String intToString(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            sb.append('-');
            num = -num;
        }

        String str = Integer.toString(num);
        int count = 0;
        StringBuilder result = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            result.insert(0, str.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                result.insert(0, ',');
                count = 0;
            }
        }

        return sb.toString() + result.toString();
    }

    public static void main(String[] args) {
        int num = -10099870;
        String str = intToString(num);
        System.out.println(str);
    }
}