public class NumberFormatter {
    public static String int2str(int num) {
        String s = String.valueOf(num);
        int length = s.length();
        int newl;
        if (length % 3 == 0) {
            newl = length + (length / 3) - 1;
        } else {
            newl = length + (length / 3);
        }

        char[] result = new char[newl];
        int counter = 0;
        int j = newl - 1;

        for (int i = length - 1; i >= 0; i--) {
            counter += 1;
            result[j] = s.charAt(i);
            if (counter % 3 == 0) {
                result[j] = ',';
                j--;
            }
            j--;
        }

        return new String(result);
    }
}