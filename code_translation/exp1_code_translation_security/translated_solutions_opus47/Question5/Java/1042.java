public class Main {
    public static String intToString(int num) {
        char[] str = new char[100];
        int i = 0;
        int j = 0;
        int temp = num;
        int count = 0;
        
        while (temp != 0) {
            str[i] = (char) (temp % 10 + '0');
            temp = temp / 10;
            i++;
            count++;
            if (count == 3 && temp != 0) {
                str[i] = ',';
                i++;
                count = 0;
            }
        }
        
        int length = i;
        i--;
        while (j < i) {
            char tempChar = str[j];
            str[j] = str[i];
            str[i] = tempChar;
            j++;
            i--;
        }
        
        return new String(str, 0, length);
    }
    
    public static void main(String[] args) {
        System.out.println(intToString(7000000));
    }
}