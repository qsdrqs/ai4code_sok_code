public class Main {

    /**
     * Reverses the input string.
     * 
     * @param str The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverseStr(String str) {
        char[] arr = str.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    /**
     * Converts an integer into a string and adds commas where appropriate.
     * 
     * @param num The input integer.
     * @return The string representation of the integer with commas.
     */
    public static String addCommas(int num) {
        StringBuilder sb = new StringBuilder();
        String str = Integer.toString(num);
        int count = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                sb.append(',');
                count = 0;
            }
        }
        return reverseStr(sb.toString());
    }

    public static void main(String[] args) {
        int num = 1000000;
        String str = addCommas(num);
        System.out.println(str);
    }
}