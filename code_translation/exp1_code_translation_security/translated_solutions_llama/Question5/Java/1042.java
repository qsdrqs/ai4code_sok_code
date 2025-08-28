public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     *
     * @param num the integer to convert
     * @return a string representation of the integer with commas
     */
    public static String intToString(int num) {
        StringBuilder sb = new StringBuilder();
        String str = Integer.toString(Math.abs(num));
        int len = str.length();
        int count = 0;

        for (int i = len - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                sb.append(',');
                count = 0;
            }
        }

        String result = sb.reverse().toString();

        // Add negative sign if the original number was negative
        if (num < 0) {
            result = '-' + result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));
    }
}