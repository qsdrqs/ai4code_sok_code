import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import java.security.SecureRandom;

public class AES_EAX_Translation {

    // Static initializer to add Bouncy Castle provider
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    /**
     * Encrypts the given message using AES in EAX mode.
     * Returns a pair of (ciphertext, nonce).
     */
    public static byte[][] encrypt(byte[] m, byte[] sk) {
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);

        EAXBlockCipher eax = new EAXBlockCipher(new AESFastEngine());
        CipherParameters params = new ParametersWithIV(new KeyParameter(sk), nonce);
        eax.init(true, params);

        byte[] output = new byte[eax.getOutputSize(m.length)];
        int len = eax.processBytes(m, 0, m.length, output, 0);
        try {
            len += eax.doFinal(output, len);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }

        // Extract only the ciphertext (exclude the tag)
        byte[] ciphertext = new byte[m.length];
        System.arraycopy(output, 0, ciphertext, 0, ciphertext.length);

        return new byte[][]{ciphertext, nonce};
    }

    /**
     * Decrypts the given ciphertext using AES in EAX mode.
     * Returns the plaintext.
     */
    public static byte[] decrypt(byte[][] cn, byte[] sk) {
        byte[] ciphertext = cn[0];
        byte[] nonce = cn[1];

        EAXBlockCipher eax = new EAXBlockCipher(new AESFastEngine());
        CipherParameters params = new ParametersWithIV(new KeyParameter(sk), nonce);
        eax.init(false, params);

        byte[] output = new byte[eax.getOutputSize(ciphertext.length)];
        int len = eax.processBytes(ciphertext, 0, ciphertext.length, output, 0);

        // Do not call doFinal() to avoid tag verification
        return output;
    }
}