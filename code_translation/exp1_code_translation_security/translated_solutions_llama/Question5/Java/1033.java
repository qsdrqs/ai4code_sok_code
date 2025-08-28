public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     * 
     * @param num The integer to be converted.
     * @return A string representation of the integer with commas.
     */
    public static String numToStr(int num) {
        String str = Integer.toString(num);
        StringBuilder sb = new StringBuilder(str);

        if (num < 0) {
            // Handle negative numbers
            int length = str.length() - 1; // exclude the negative sign
            int numCommas = (length - 1) / 3;
            for (int i = length; i > 0; i--) {
                if ((length - i) % 3 == 0) {
                    sb.insert(i, ',');
                }
            }
        } else {
            // Handle non-negative numbers
            int length = str.length();
            int numCommas = (length - 1) / 3;
            for (int i = length; i > 0; i--) {
                if ((length - i) % 3 == 0) {
                    sb.insert(i, ',');
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 7042;
        String str = numToStr(num);
        System.out.println("Output: " + str);
    }
}