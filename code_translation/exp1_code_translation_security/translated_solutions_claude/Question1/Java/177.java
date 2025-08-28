import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

public class AESEncryption {
    
    public static class EncryptionResult {
        public final byte[] ciphertext;
        public final byte[] nonce;
        
        public EncryptionResult(byte[] ciphertext, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.nonce = nonce;
        }
    }
    
    public static EncryptionResult encrypt(byte[] message, byte[] secretKey) {
        try {
            // Create EAX cipher
            EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
            
            // Generate random nonce (12 bytes is standard for EAX)
            byte[] nonce = new byte[12];
            new SecureRandom().nextBytes(nonce);
            
            // Initialize cipher for encryption
            AEADParameters params = new AEADParameters(new KeyParameter(secretKey), 128, nonce);
            cipher.init(true, params);
            
            // Encrypt and authenticate
            byte[] ciphertext = new byte[cipher.getOutputSize(message.length)];
            int len = cipher.processBytes(message, 0, message.length, ciphertext, 0);
            len += cipher.doFinal(ciphertext, len);
            
            // Trim ciphertext to actual length
            byte[] result = new byte[len];
            System.arraycopy(ciphertext, 0, result, 0, len);
            
            return new EncryptionResult(result, nonce);
            
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    public static byte[] decrypt(EncryptionResult encryptionResult, byte[] secretKey) {
        return decrypt(encryptionResult.ciphertext, encryptionResult.nonce, secretKey);
    }
    
    public static byte[] decrypt(byte[] ciphertext, byte[] nonce, byte[] secretKey) {
        try {
            // Create EAX cipher
            EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
            
            // Initialize cipher for decryption
            AEADParameters params = new AEADParameters(new KeyParameter(secretKey), 128, nonce);
            cipher.init(false, params);
            
            // Decrypt and verify
            byte[] plaintext = new byte[cipher.getOutputSize(ciphertext.length)];
            int len = cipher.processBytes(ciphertext, 0, ciphertext.length, plaintext, 0);
            len += cipher.doFinal(plaintext, len);
            
            // Trim plaintext to actual length
            byte[] result = new byte[len];
            System.arraycopy(plaintext, 0, result, 0, len);
            
            return result;
            
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException("Decryption failed - invalid ciphertext or authentication tag", e);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}