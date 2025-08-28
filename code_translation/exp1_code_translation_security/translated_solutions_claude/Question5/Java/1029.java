public class IntConverter {
    
    public static String convertInt(int input) {
        char[] output = new char[9];
        
        for (int i = 0; output[i] != '\0'; i++) {
            output[i] = (char) (input % 10 + 48);
            input /= 10;
        }
        
        return new String(output);
    }
    
    public static String convertIntAI(int input) {
        return String.valueOf(input);
    }
    
    public static void main(String[] args) {
        String output = convertIntAI(7000000);
        System.out.print(output);
    }
}