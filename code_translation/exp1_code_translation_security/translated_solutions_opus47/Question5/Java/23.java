public class Main {
    public static String intToString(int x) {
        String tempstring = String.format("%d", x);
        int len = (int)(Math.ceil(Math.log10(x)) + 1);
        // the above INCLUDES the terminating chr
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % 3 == 0) { // yes comma
                result.append(",");
            }
            // result.append(tempstring.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.printf("%s", intToString(100000));
        System.out.printf("test\n");
    }
}