public class Main {

    /**
     * Converts a signed integer to its string representation.
     * 
     * @param num The input integer.
     * @return The string representation of the integer.
     */
    public static String strOfInt(int num) {
        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((char) (num % 10 + '0'));
            num /= 10;
        } while (num != 0);
        if (isNeg) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(strOfInt(7000000));
    }
}