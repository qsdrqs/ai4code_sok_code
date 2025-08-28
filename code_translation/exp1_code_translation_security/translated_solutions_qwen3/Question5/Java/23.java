public class Main {

    public static String intToString(int x) {
        String temp = Integer.toString(x);
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = temp.length() - 1; i >= 0; i--) {
            sb.append(temp.charAt(i));
            count++;
            if (count % 3 == 0 && i != 0) {
                sb.append(',');
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.print(intToString(100000));
        System.out.println("test");
    }
}