public class Main {
    
    public static String intToString(int input) {
        String buffer = String.valueOf(input);
        
        System.out.printf("you have entered: %s", buffer);
        
        return null;
    }
    
    public static void main(String[] args) {
        intToString(7000000);
    }
}