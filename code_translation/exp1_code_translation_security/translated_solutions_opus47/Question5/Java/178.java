public class Main {
    public static void main(String[] args) {
        System.out.print(convertToString(2000));
    }
    
    public static String convertToString(int num) {
        String string = String.valueOf(num);
        int numOfCommas = Math.round((float)(string.length() / 3));
        System.out.println(numOfCommas);
        
        int totalLength = string.length() + 1 + numOfCommas;
        char[] newString = new char[totalLength];
        
        for (int i = 0; i < totalLength; i++) {
            if (i < string.length()) {
                newString[i] = string.charAt(i);
                System.out.print((int) string.charAt(i));
            }
            if (i % 3 == 0 && i != totalLength) {
                System.out.print("s");
                if (i + 1 < totalLength) {
                    newString[i + 1] = ',';
                }
            }
        }
        return new String(newString);
    }
}