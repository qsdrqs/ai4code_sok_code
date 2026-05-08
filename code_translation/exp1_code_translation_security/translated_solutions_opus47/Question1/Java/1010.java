/*
 * Import cryptographic libraries
 * Note: Requires BouncyCastle library (org.bouncycastle:bcprov-jdk15on) 
 * because EAX mode is not natively supported in standard Java crypto.
 */
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class AESEncryption {

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Helper class to hold the encryption result (ciphertext + nonce).
     * Used to return multiple values like a Python tuple.
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
     * Function to encrypt a string using a symmetric key
     */
    public static EncryptionResult encrypt(byte[] key, String data) throws Exception {
        // Convert the data to bytes
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        // Generate a random nonce (16 bytes, matching PyCryptodome default)
        byte[] nonce = new byte[16];
        secureRandom.nextBytes(nonce);

        // Create a cipher object using the key
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters parameters = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(true, parameters);

        // Encrypt the data (output includes ciphertext + 16-byte auth tag appended)
        byte[] ciphertext = new byte[cipher.getOutputSize(dataBytes.length)];
        int len = cipher.processBytes(dataBytes, 0, dataBytes.length, ciphertext, 0);
        cipher.doFinal(ciphertext, len);

        // Return the ciphertext and the nonce
        return new EncryptionResult(ciphertext, nonce);
    }

    /**
     * Function to decrypt a string
     */
    public static byte[] decrypt(byte[] key, byte[] nonce, byte[] ciphertext) throws Exception {
        // Create a cipher object using the key
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters parameters = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(false, parameters);

        // Decrypt the data
        byte[] data = new byte[cipher.getOutputSize(ciphertext.length)];
        int len = cipher.processBytes(ciphertext, 0, ciphertext.length, data, 0);
        cipher.doFinal(data, len);

        // Return the decrypted data
        return data;
    }

    /**
     * Function to generate a symmetric key
     */
    public static byte[] generateKey() {
        // Generate a random key (16 bytes = AES-128)
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        //
        return key;
    }

    /*
    // Example
    public static void main(String[] args) throws Exception {
        byte[] key = generateKey();
        String testData = "Jim's test";
        EncryptionResult encrypted = encrypt(key, testData);
        byte[] restoredData = decrypt(key, encrypted.nonce, encrypted.ciphertext);
        System.out.println(new String(restoredData, StandardCharsets.UTF_8));
    }
    */
}