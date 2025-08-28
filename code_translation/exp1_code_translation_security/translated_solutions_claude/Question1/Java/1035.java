import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESEncryption {
    private static final byte[] key = "Sixteen byte key".getBytes();
    
    public static class EncryptResult {
        public final byte[] ciphertext;
        public final byte[] tag;
        public final byte[] nonce;
        
        public EncryptResult(byte[] ciphertext, byte[] tag, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.tag = tag;
            this.nonce = nonce;
        }
    }
    
    public static EncryptResult encrypt(byte[] key, byte[] msg) throws InvalidCipherTextException {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        
        // Generate random nonce
        byte[] nonce = new byte[16]; // EAX typically uses 16-byte nonce
        new SecureRandom().nextBytes(nonce);
        
        // Initialize cipher for encryption
        AEADParameters params = new AEADParameters(new KeyParameter(key), 128, nonce); // 128-bit tag
        cipher.init(true, params);
        
        // Encrypt and get tag
        byte[] output = new byte[cipher.getOutputSize(msg.length)];
        int len = cipher.processBytes(msg, 0, msg.length, output, 0);
        len += cipher.doFinal(output, len);
        
        // Split ciphertext and tag
        byte[] ciphertext = Arrays.copyOf(output, len - 16); // Remove 16-byte tag
        byte[] tag = Arrays.copyOfRange(output, len - 16, len); // Extract 16-byte tag
        
        return new EncryptResult(ciphertext, tag, nonce);
    }
    
    public static byte[] decrypt(byte[] key, byte[] ciphertext, byte[] nonce, byte[] tag) 
            throws InvalidCipherTextException {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        
        // Initialize cipher for decryption
        AEADParameters params = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(false, params);
        
        // Combine ciphertext and tag for decryption
        byte[] input = new byte[ciphertext.length + tag.length];
        System.arraycopy(ciphertext, 0, input, 0, ciphertext.length);
        System.arraycopy(tag, 0, input, ciphertext.length, tag.length);
        
        // Decrypt and verify tag
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);
        
        return Arrays.copyOf(output, len);
    }
    
    public static void main(String[] args) {
        try {
            EncryptResult result = encrypt(key, "message".getBytes());
            byte[] decrypted = decrypt(key, result.ciphertext, result.nonce, result.tag);
            System.out.println(new String(decrypted));
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
    }
}