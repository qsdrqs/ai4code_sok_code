import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESEncryption {
    
    public static class EncryptionResult {
        public byte[] ciphertext;
        public byte[] tag;
        
        public EncryptionResult(byte[] ciphertext, byte[] tag) {
            this.ciphertext = ciphertext;
            this.tag = tag;
        }
    }
    
    public static EncryptionResult encodeString(String message, byte[] key, byte[] nonce) {
        try {
            // Create cipher instance for AES-GCM (closest to EAX mode)
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce); // 128-bit tag
            
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
            
            // Encode string as bytes and encrypt
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedData = cipher.doFinal(messageBytes);
            
            // In GCM mode, the tag is appended to the ciphertext
            // Split the result into ciphertext and tag
            int tagLength = 16; // 128 bits = 16 bytes
            byte[] ciphertext = Arrays.copyOf(encryptedData, encryptedData.length - tagLength);
            byte[] tag = Arrays.copyOfRange(encryptedData, encryptedData.length - tagLength, encryptedData.length);
            
            return new EncryptionResult(ciphertext, tag);
            
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    public static String decodeString(EncryptionResult encryptionResult, byte[] key, byte[] nonce) {
        try {
            // Create cipher instance for AES-GCM
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce);
            
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
            
            // Combine ciphertext and tag for decryption
            byte[] encryptedData = new byte[encryptionResult.ciphertext.length + encryptionResult.tag.length];
            System.arraycopy(encryptionResult.ciphertext, 0, encryptedData, 0, encryptionResult.ciphertext.length);
            System.arraycopy(encryptionResult.tag, 0, encryptedData, encryptionResult.ciphertext.length, encryptionResult.tag.length);
            
            // Decrypt and verify
            byte[] decryptedData = cipher.doFinal(encryptedData);
            
            // Decode from bytes to string
            return new String(decryptedData, StandardCharsets.UTF_8);
            
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    public static byte[] getRandomBytes(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
    
    public static void main(String[] args) {
        String originalMessage = "secret data";
        byte[] nonce = getRandomBytes(16);
        byte[] key = getRandomBytes(16);
        
        System.out.println(originalMessage);
        EncryptionResult ciphertext = encodeString(originalMessage, key, nonce);
        String decodedString = decodeString(ciphertext, key, nonce);
        System.out.println(decodedString);
    }
}