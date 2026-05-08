/*
Write a java program to convert int into to string
*/

public class Main {
    public static String intotostr(int n) {
        String str = String.format("%d", n);
        return str;
    }
    
    public static void main(String[] args) {
        int num = 123;
        String result = intotostr(num);
        System.out.println(result);
    }
}