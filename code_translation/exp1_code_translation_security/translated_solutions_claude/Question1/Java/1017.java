import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AESEncryption {
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12; // 96 bits
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    
    // Class to hold the encryption result (equivalent to Python tuple)
    public static class EncryptionResult {
        public final byte[] nonce;
        public final byte[] tag;
        public final byte[] ciphertext;
        
        public EncryptionResult(byte[] nonce, byte[] tag, byte[] ciphertext) {
            this.nonce = nonce;
            this.tag = tag;
            this.ciphertext = ciphertext;
        }
    }
    
    // Function to encrypt a given string using a given symmetric key
    public static EncryptionResult encrypt(byte[] key, byte[] plaintext) {
        try {
            // Generate random nonce
            byte[] nonce = new byte[GCM_IV_LENGTH];
            SecureRandom.getInstanceStrong().nextBytes(nonce);
            
            // Create cipher instance
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            
            // Initialize cipher for encryption
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
            
            // Encrypt the plaintext
            byte[] encryptedData = cipher.doFinal(plaintext);
            
            // In GCM mode, the tag is appended to the ciphertext
            // We need to separate them
            int ciphertextLength = encryptedData.length - GCM_TAG_LENGTH;
            byte[] ciphertext = new byte[ciphertextLength];
            byte[] tag = new byte[GCM_TAG_LENGTH];
            
            System.arraycopy(encryptedData, 0, ciphertext, 0, ciphertextLength);
            System.arraycopy(encryptedData, ciphertextLength, tag, 0, GCM_TAG_LENGTH);
            
            return new EncryptionResult(nonce, tag, ciphertext);
            
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    // Function to decrypt a given ciphertext using a given symmetric key
    public static byte[] decrypt(byte[] key, EncryptionResult encryptionResult) {
        try {
            // Extract components
            byte[] nonce = encryptionResult.nonce;
            byte[] tag = encryptionResult.tag;
            byte[] ciphertext = encryptionResult.ciphertext;
            
            // Combine ciphertext and tag (GCM expects them together)
            byte[] ciphertextWithTag = new byte[ciphertext.length + tag.length];
            System.arraycopy(ciphertext, 0, ciphertextWithTag, 0, ciphertext.length);
            System.arraycopy(tag, 0, ciphertextWithTag, ciphertext.length, tag.length);
            
            // Create cipher instance
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
            
            // Initialize cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
            
            // Decrypt and verify
            byte[] decryptedData = cipher.doFinal(ciphertextWithTag);
            
            return decryptedData;
            
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    // Alternative decrypt method that takes individual components
    public static byte[] decrypt(byte[] key, byte[] nonce, byte[] tag, byte[] ciphertext) {
        return decrypt(key, new EncryptionResult(nonce, tag, ciphertext));
    }
    
    // Example usage
    public static void main(String[] args) {
        try {
            // Example key (32 bytes for AES-256)
            byte[] key = new byte[32];
            SecureRandom.getInstanceStrong().nextBytes(key);
            
            // Example plaintext
            String message = "Hello, World!";
            byte[] plaintext = message.getBytes("UTF-8");
            
            // Encrypt
            EncryptionResult result = encrypt(key, plaintext);
            System.out.println("Encryption successful");
            
            // Decrypt
            byte[] decrypted = decrypt(key, result);
            String decryptedMessage = new String(decrypted, "UTF-8");
            
            System.out.println("Original: " + message);
            System.out.println("Decrypted: " + decryptedMessage);
            System.out.println("Match: " + message.equals(decryptedMessage));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}