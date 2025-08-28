import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCrypto {

    // Function to encrypt a string using a symmetric key
    public static byte[] encrypt(SecretKey key, String data) throws Exception {
        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] nonce = new byte[12]; // GCM nonce length is 12 bytes
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        GCMParameterSpec spec = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        // Encrypt the data
        byte[] ciphertext = cipher.doFinal(data.getBytes());
        // Return the ciphertext and the nonce
        byte[] result = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, result, 0, nonce.length);
        System.arraycopy(ciphertext, 0, result, nonce.length, ciphertext.length);
        return result;
    }

    // Function to decrypt a string
    public static String decrypt(SecretKey key, byte[] encryptedData) throws Exception {
        // Extract nonce and ciphertext
        byte[] nonce = new byte[12];
        byte[] ciphertext = new byte[encryptedData.length - 12];
        System.arraycopy(encryptedData, 0, nonce, 0, nonce.length);
        System.arraycopy(encryptedData, nonce.length, ciphertext, 0, ciphertext.length);
        
        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        // Decrypt the data
        byte[] data = cipher.doFinal(ciphertext);
        // Return the decrypted data
        return new String(data);
    }

    // Function to generate a symmetric key
    public static SecretKey generateKey() throws Exception {
        // Generate a random key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES key size
        return keyGen.generateKey();
    }

    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            String testData = "Jim's test";
            byte[] encrypted = encrypt(key, testData);
            String restoredData = decrypt(key, encrypted);
            System.out.println(restoredData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}