public class Main {

    /**
     * Returns the number of digits in the given integer.
     * 
     * @param n the input integer
     * @return the number of digits in n
     */
    public static int numDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int cnt = 0;
        while (n != 0) {
            n = n / 10;
            cnt++;
        }
        return cnt;
    }

    /**
     * Returns a string representation of the given integer with commas at every third digit.
     * 
     * @param num the input integer
     * @return a string representation of num with commas
     */
    public static String returnString(int num) {
        String numStr = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = numStr.length() - 1; i >= 0; i--) {
            sb.append(numStr.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                sb.append(',');
                count = 0;
            }
        }
        // Reverse the string to get the correct order
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int num = 7000000;
        String str = returnString(num);
        System.out.println(str);
    }
}