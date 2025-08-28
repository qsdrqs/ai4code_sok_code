import java.security.GeneralSecurityException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Rough, line-for-line translation of the original Python code.
 * (Assumes the Bouncy Castle provider is available on the class-path.)
 */
public final class AesEaxUtil {

    /*  Register Bouncy-Castle once.  */
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Simple container that holds the ciphertext and the nonce produced during
     * encryption (i.e., the equivalent of Python’s “(c, nonce)” tuple).
     */
    public static final class CipherNonce {
        private final byte[] cipherText;
        private final byte[] nonce;

        public CipherNonce(byte[] cipherText, byte[] nonce) {
            this.cipherText = cipherText;
            this.nonce      = nonce;
        }

        public byte[] getCipherText() { return cipherText; }
        public byte[] getNonce()      { return nonce;      }
    }

    /**
     * Encrypt a message with AES/EAX.
     *
     * @param message plaintext bytes
     * @param key     raw AES key bytes (16/24/32 bytes for AES-128/192/256)
     * @return CipherNonce  (ciphertext, nonce)
     */
    public static CipherNonce encrypt(byte[] message, byte[] key)
            throws GeneralSecurityException {

        Cipher cipher       = Cipher.getInstance("AES/EAX/NoPadding", "BC");
        SecretKeySpec kSpec = new SecretKeySpec(key, "AES");

        /*  Let the provider pick a fresh random nonce.  */
        cipher.init(Cipher.ENCRYPT_MODE, kSpec);

        byte[] cipherText = cipher.doFinal(message);
        byte[] nonce      = cipher.getIV();   // same as “cipher.nonce” in Python

        return new CipherNonce(cipherText, nonce);
    }

    /**
     * Decrypt a ciphertext previously produced by {@link #encrypt}.
     *
     * @param cn  the (ciphertext, nonce) pair
     * @param key same key that was used for encryption
     * @return    the recovered plaintext
     */
    public static byte[] decrypt(CipherNonce cn, byte[] key)
            throws GeneralSecurityException {

        Cipher cipher       = Cipher.getInstance("AES/EAX/NoPadding", "BC");
        SecretKeySpec kSpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv  = new IvParameterSpec(cn.getNonce());

        cipher.init(Cipher.DECRYPT_MODE, kSpec, iv);

        return cipher.doFinal(cn.getCipherText());
    }

    /* Hide constructor ‑- utility class. */
    private AesEaxUtil() {}
}