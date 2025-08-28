import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

import java.security.SecureRandom;
import java.security.Security;
import java.nio.charset.StandardCharsets;

public class AESEncryption {

    static {
        // Register Bouncy Castle provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    // 16-byte key (same as in Python)
    private static final byte[] key = "Sixteen byte key".getBytes(StandardCharsets.UTF_8);

    /**
     * Encrypts a message using AES in EAX mode.
     *
     * @param key  The 16-byte AES key.
     * @param msg  The plaintext message to encrypt.
     * @return     An Object array containing: [ciphertext, tag, nonce]
     */
    public static Object[] encrypt(byte[] key, byte[] msg) {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESFastEngine());
        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);

        cipher.init(true, new ParametersWithIV(new KeyParameter(key), nonce));

        byte[] ciphertext = new byte[cipher.getOutputSize(msg.length)];
        int bytesWritten = cipher.processBytes(msg, 0, msg.length, ciphertext, 0);
        try {
            cipher.doFinal(ciphertext, bytesWritten);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed: " + e.getMessage());
        }

        byte[] tag = cipher.getMac();

        return new Object[] { ciphertext, tag, nonce };
    }

    /**
     * Decrypts a message using AES in EAX mode.
     *
     * @param key       The 16-byte AES key.
     * @param ciphertext The encrypted message.
     * @param nonce      The nonce used during encryption.
     * @param tag        The authentication tag (can be null to skip verification).
     * @return           The decrypted plaintext.
     */
    public static byte[] decrypt(byte[] key, byte[] ciphertext, byte[] nonce, byte[] tag) {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESFastEngine());
        cipher.init(false, new ParametersWithIV(new KeyParameter(key), nonce));

        byte[] plaintext = new byte[cipher.getOutputSize(ciphertext.length)];
        int bytesWritten = cipher.processBytes(ciphertext, 0, ciphertext.length, plaintext, 0);
        try {
            cipher.doFinal(plaintext, bytesWritten);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage());
        }

        if (tag != null) {
            byte[] calculatedTag = cipher.getMac();
            if (!Arrays.constantTimeAreEqual(tag, calculatedTag)) {
                throw new RuntimeException("Tag verification failed");
            }
        }

        return plaintext;
    }

    public static void main(String[] args) {
        // Encrypt the message
        Object[] result = encrypt(key, "message".getBytes(StandardCharsets.UTF_8));
        byte[] ciphertext = (byte[]) result[0];
        byte[] tag = (byte[]) result[1];
        byte[] nonce = (byte[]) result[2];

        // Decrypt and print the result
        byte[] decrypted = decrypt(key, ciphertext, nonce, tag);
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
    }
}