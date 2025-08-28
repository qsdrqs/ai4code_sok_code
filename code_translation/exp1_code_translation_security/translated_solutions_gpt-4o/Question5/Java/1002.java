public class NumberConverter {

    private static final int BUFFER_SIZE = 1024;

    public static String convert(long value) {
        StringBuilder buf1 = new StringBuilder(String.format("%d", value));
        StringBuilder buf2 = new StringBuilder(BUFFER_SIZE);
        int len = buf1.length();
        int idx = 0;
        for (int i = 0; i < len; i++) {
            buf2.append(buf1.charAt(i));
            int dist = len - i - 1;
            if (dist % 3 == 0 && dist != 0) {
                buf2.append(',');
            }
        }
        return buf2.toString();
    }

    public static void main(String[] args) {
        String test = convert(1000);
        System.out.println(test);
    }
}