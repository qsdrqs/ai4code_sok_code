public class IntToString {
    
    public static String intToString(int a) {
        StringBuilder str = new StringBuilder();
        
        String buf = String.valueOf(a);
        int bufIdx = 0;
        int len = buf.length();
        
        String actualBuf = buf;
        if (len > 0 && buf.charAt(0) == '-') {
            str.append(buf.charAt(0));
            actualBuf = buf.substring(1);
            len -= 1;
        }
        
        for (bufIdx = 0; bufIdx < len; bufIdx++) {
            if (bufIdx != 0 && (len - bufIdx) % 3 == 0) {
                str.append(',');
            }
            str.append(actualBuf.charAt(bufIdx));
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