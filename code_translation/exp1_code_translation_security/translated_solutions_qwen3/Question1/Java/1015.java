import java.util.Base64;

// Assuming a compatible Fernet implementation is available in the classpath
// For example, using a Java Fernet library like:
// https://github.com/0xbadcafe/fernet-java or a custom implementation

public class FernetEncryption {

    /**
     * Encrypts the given data using the provided Fernet key.
     *
     * @param data The data to encrypt (as bytes).
     * @param key  The Fernet key (as a Base64-encoded string).
     * @return The encrypted data as a byte array.
     */
    public static byte[] encrypt(byte[] data, String key) {
        return Fernet.encrypt(data, key);
    }

    /**
     * Decrypts the given encrypted data using the provided Fernet key.
     *
     * @param data The encrypted data (as bytes).
     * @param key  The Fernet key (as a Base64-encoded string).
     * @return The decrypted data as a byte array.
     */
    public static byte[] decrypt(byte[] data, String key) {
        return Fernet.decrypt(data, key);
    }

    /**
     * Main method to demonstrate encryption.
     * Generates a Fernet key and encrypts the string "/safedir/test.txt".
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String data = "/safedir/test.txt";
        String key = Fernet.generateKey(); // Generate a new Fernet key
        byte[] encrypted = encrypt(data.getBytes(), key); // Encrypt the data

        // Optionally, print the encrypted result in Base64 format
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
    }
}