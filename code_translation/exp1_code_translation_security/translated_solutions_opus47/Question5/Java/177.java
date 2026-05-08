public class NumberFormatter {

    public static String int2str(int num) {
        // Convert int to string (replaces both snprintf calls in C)
        String str = String.valueOf(num);
        int length = str.length();

        int newl;
        if (length % 3 == 0) {
            newl = length + length / 3 - 1;
        } else {
            newl = length + (length / 3);
        }

        // char[] replaces C's malloc-allocated char array
        char[] result = new char[newl];
        int counter = 0;
        int j = newl - 1;
        for (int i = length - 1; i >= 0; i--) {
            counter += 1;
            result[j] = str.charAt(i);
            if (counter % 3 == 0) {
                result[j--] = ',';
            }
            j--;
        }
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(int2str(1234567));
    }
}