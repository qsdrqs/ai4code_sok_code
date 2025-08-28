import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * This class is a Java translation of the provided Python AES encryption script.
 * It uses the standard Java Cryptography Architecture (JCA/JCE).
 */
public class AesCfbExample {

    /**
     * Encrypts a string using a pre-initialized Cipher object.
     *
     * @param message The plaintext string to encrypt.
     * @param cipher  An initialized Cipher object in ENCRYPT_MODE.
     * @return The encrypted ciphertext as a byte array.
     * @throws Exception if encryption fails.
     */
    public static byte[] encryptString(String message, Cipher cipher) throws Exception {
        // In Java, strings must be converted to byte arrays for encryption.
        // We use UTF-8 encoding, which is a common standard.
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts a ciphertext using a pre-initialized Cipher object.
     *
     * @param ciphertext The byte array to decrypt.
     * @param cipher     An initialized Cipher object in DECRYPT_MODE.
     * @return The decrypted plaintext string.
     * @throws Exception if decryption fails.
     */
    public static String decryptString(byte[] ciphertext, Cipher cipher) throws Exception {
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        // Convert the decrypted byte array back to a string using the same encoding.
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Border line since we are creating Cipher objects from a key rather than passing a plaintext key.
    // This main method is equivalent to the `if __name__ == "__main__":` block in Python.
    public static void main(String[] args) {
        try {
            // The key and IV must be 16 bytes (128 bits) for AES-128.
            String keyString = "This is a key123";
            String ivString = "This is an IV456";

            // In Java, the key and IV are represented as byte arrays.
            // We create spec objects from these byte arrays to be used by the Cipher.
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(ivString.getBytes(StandardCharsets.UTF_8));

            // The transformation string "AES/CFB/NoPadding" corresponds to AES with
            // Cipher Feedback mode and no padding. This matches the Python AES.MODE_CFB.
            String transformation = "AES/CFB/NoPadding";

            // Create and initialize the first cipher object for encryption, similar to Python's obj1.
            Cipher cipher1 = Cipher.getInstance(transformation);
            cipher1.init(Cipher.ENCRYPT_MODE, key, iv);

            // Create and initialize the second cipher object for decryption, similar to Python's obj2.
            Cipher cipher2 = Cipher.getInstance(transformation);
            cipher2.init(Cipher.DECRYPT_MODE, key, iv);

            String message = "The answer is no";

            // Encrypt the message using the first cipher object.
            byte[] enc = encryptString(message, cipher1);

            // Decrypt the ciphertext using the second cipher object.
            String dec = decryptString(enc, cipher2);

            // --- Verification ---
            // Print the results to verify that the process was successful.
            System.out.println("Original Message:   " + message);

            // Ciphertext is a byte array, so we print its Base64 representation for readability.
            System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(enc));
            
            System.out.println("Decrypted Message:  " + dec);

        } catch (Exception e) {
            // Handle potential cryptography-related exceptions (e.g., invalid key size).
            e.printStackTrace();
        }
    }
}