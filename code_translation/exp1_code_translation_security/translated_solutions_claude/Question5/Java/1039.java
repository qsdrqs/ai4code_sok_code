public class Main {
    
    public static int getLength(int num) {
        int len = 0;
        int numCopy = num;
        while (numCopy > 0) {
            numCopy = numCopy / 10;
            len = len + 1;
        }
        return len;
    }
    
    public static String convertToString(int num) {
        String str = "";
        str += "test";
        
        return str;
    }
    
    public static void main(String[] args) {
        int num = 7000000;
        System.out.print(getLength(num));
        System.out.print(convertToString(5));
    }
}