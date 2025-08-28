import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.SecureRandom;

public class AESUtils {

    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Encrypts the given plaintext using AES in EAX mode with the provided key.
     * Returns an array containing: [nonce, tag, ciphertext]
     */
    public static byte[][] encrypt(byte[] key, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);

        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(nonce));
        byte[] ciphertextWithTag = cipher.doFinal(plaintext);

        // Split the result into ciphertext and tag (last 16 bytes)
        int tagLength = 16;
        byte[] ciphertext = new byte[ciphertextWithTag.length - tagLength];
        byte[] tag = new byte[tagLength];

        System.arraycopy(ciphertextWithTag, 0, ciphertext, 0, ciphertext.length);
        System.arraycopy(ciphertextWithTag, ciphertext.length, tag, 0, tag.length);

        return new byte[][]{nonce, tag, ciphertext};
    }

    /**
     * Decrypts the given ciphertext using AES in EAX mode with the provided key.
     * The input is an array containing: [nonce, tag, ciphertext]
     * Returns the original plaintext if the tag is valid.
     */
    public static byte[] decrypt(byte[] key, byte[][] ciphertext) throws Exception {
        byte[] nonce = ciphertext[0];
        byte[] tag = ciphertext[1];
        byte[] ciphertextBytes = ciphertext[2];

        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(nonce));

        // Combine ciphertext and tag for decryption
        byte[] ciphertextWithTag = new byte[ciphertextBytes.length + tag.length];
        System.arraycopy(ciphertextBytes, 0, ciphertextWithTag, 0, ciphertextBytes.length);
        System.arraycopy(tag, 0, ciphertextWithTag, ciphertextBytes.length, tag.length);

        return cipher.doFinal(ciphertextWithTag);
    }
}