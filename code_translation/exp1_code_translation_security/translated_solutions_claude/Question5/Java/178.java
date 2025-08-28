public class Main {
    public static void main(String[] args) {
        System.out.print(convertToString(2000));
    }
    
    public static String convertToString(int num) {
        String string = String.valueOf(num);
        int numOfCommas = (int) Math.round(string.length() / 3.0);
        System.out.println(numOfCommas);
        
        StringBuilder newString = new StringBuilder();
        
        for (int i = 0; i < string.length() + 1 + numOfCommas; i++) {
            if (i < string.length()) {
                newString.append(string.charAt(i));
                System.out.print((int) string.charAt(i));
            }
            
            if (i % 3 == 0 && i != string.length() + 1 + numOfCommas - 1) {
                System.out.print("s");
                newString.append(",");
            }
        }
        
        return newString.toString();
    }
}