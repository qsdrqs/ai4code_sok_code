public class IntToStringConverter {

    public static String intToString(int a) {
        StringBuilder str = new StringBuilder();
        String buf = String.valueOf(a);
        int len = buf.length();
        int idx = 0;

        if (len > 0 && buf.charAt(0) == '-') {
            str.append(buf.charAt(0));
            idx++;
        }

        for (int bufIdx = idx; bufIdx < len; bufIdx++) {
            if (bufIdx != idx && (len - bufIdx) % 3 == 0) {
                str.append(',');
            }
            str.append(buf.charAt(bufIdx));
        }

        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToString(7000000));
        System.out.println(intToString(700000));
        System.out.println(intToString(70000));
        System.out.println(intToString(7000));
        System.out.println(intToString(700));
        System.out.println(intToString(70));
        System.out.println(intToString(7));
        System.out.println(intToString(0));
        System.out.println(intToString(-7000000));
        System.out.println(intToString(-700000));
        System.out.println(intToString(-70000));
        System.out.println(intToString(-7000));
        System.out.println(intToString(-700));
        System.out.println(intToString(-70));
        System.out.println(intToString(-7));
        System.out.println(intToString(Integer.MAX_VALUE));
        System.out.println(intToString(Integer.MIN_VALUE));
    }
}