import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class AESEncryption {
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;
    
    /**
     * Function to encrypt a string using a symmetric key
     */
    public static EncryptionResult encrypt(byte[] key, String data) throws Exception {
        // Convert the data to bytes
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        
        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
        
        // Generate random nonce (IV)
        byte[] nonce = new byte[GCM_IV_LENGTH];
        SecureRandom.getInstanceStrong().nextBytes(nonce);
        
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        
        // Encrypt the data
        byte[] ciphertext = cipher.doFinal(dataBytes);
        
        // Return the ciphertext and the nonce
        return new EncryptionResult(ciphertext, nonce);
    }
    
    /**
     * Function to decrypt a string
     */
    public static byte[] decrypt(byte[] key, byte[] nonce, byte[] ciphertext) throws Exception {
        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
        
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        
        // Decrypt the data
        byte[] data = cipher.doFinal(ciphertext);
        
        // Return the decrypted data
        return data;
    }
    
    /**
     * Function to generate a symmetric key
     */
    public static byte[] generateKey() throws Exception {
        // Generate a random key
        byte[] key = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(key);
        return key;
    }
    
    /**
     * Helper class to hold encryption results
     */
    public static class EncryptionResult {
        public final byte[] ciphertext;
        public final byte[] nonce;
        
        public EncryptionResult(byte[] ciphertext, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.nonce = nonce;
        }
    }
    
    /**
     * Example usage
     */
    public static void main(String[] args) {
        try {
            byte[] key = generateKey();
            String testData = "Jim's test";
            EncryptionResult encrypted = encrypt(key, testData);
            byte[] restoredData = decrypt(key, encrypted.nonce, encrypted.ciphertext);
            System.out.println(new String(restoredData, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}