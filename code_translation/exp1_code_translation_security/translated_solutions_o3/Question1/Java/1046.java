public class EncryptDecrypt {

    /**
     * Replicates the behaviour of the original Python encrypt(msg, key)
     */
    public static String encrypt(String msg, String key) {
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            // Mimic the Python `print(temp)` that happens **before** the append
            System.out.println(temp.toString());

            // temp = temp + c + key
            temp.append(msg.charAt(i))
                .append(key);
        }
        return temp.toString();
    }

    /**
     * Replicates the behaviour of the original Python decrypt(msg, key)
     * (note that the supplied key is actually unused, just like in the Python
     * version).
     */
    public static String decrypt(String msg, String key) {
        StringBuilder temp = new StringBuilder();
        int control = 0;

        for (int i = 0; i < msg.length(); i++) {
            if (control % 2 == 0) {
                // In Python: temp += c[0]
                temp.append(msg.charAt(i));
            }
            control++;
        }
        return temp.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("a", "b")); // -> ab
        System.out.println(decrypt("ab", "b")); // -> a
    }
}