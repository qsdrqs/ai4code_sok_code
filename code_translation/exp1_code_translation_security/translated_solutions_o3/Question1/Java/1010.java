import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;

/**
 * Utility class that mirrors the original Python example.
 * 
 * Functions supplied:
 *   • generateKey()               – produces a 16-byte random AES key
 *   • encrypt(key, data)          – returns (cipherText, nonce)
 *   • decrypt(key, nonce, cipher) – restores the original plaintext
 */
public final class AesEaxExample {

    /* Simple “tuple” used to return two values from encrypt() */
    public static final class EncryptionResult {
        private final byte[] cipherText;
        private final byte[] nonce;

        public EncryptionResult(byte[] cipherText, byte[] nonce) {
            this.cipherText = cipherText;
            this.nonce      = nonce;
        }

        public byte[] getCipherText() { return cipherText; }
        public byte[] getNonce()      { return nonce;      }
    }

    /* ---------------------------------------------------------------------- */
    /*                         Public utility methods                         */
    /* ---------------------------------------------------------------------- */

    /** Equivalent to Python’s generate_key() */
    public static byte[] generateKey() {
        byte[] key = new byte[16];             // 128-bit AES key
        new SecureRandom().nextBytes(key);
        return key;
    }

    /** Equivalent to Python’s encrypt(key, data) */
    public static EncryptionResult encrypt(byte[] key, String data)
            throws GeneralSecurityException {

        ensureBouncyCastle();

        // Random 16-byte nonce (EAX calls it a “nonce” as in the Python code)
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        AEADParameterSpec paramSpec = new AEADParameterSpec(nonce, 128); // 128-bit tag

        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BCFIPS");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

        byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return new EncryptionResult(cipherText, nonce);
    }

    /** Equivalent to Python’s decrypt(key, nonce, ciphertext) */
    public static String decrypt(byte[] key, byte[] nonce, byte[] cipherText)
            throws GeneralSecurityException {

        ensureBouncyCastle();

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        AEADParameterSpec paramSpec = new AEADParameterSpec(nonce, 128);

        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BCFIPS");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, StandardCharsets.UTF_8);
    }

    /* ---------------------------------------------------------------------- */
    /*                               Demo / test                              */
    /* ---------------------------------------------------------------------- */

    public static void main(String[] args) throws Exception {
        byte[] key = generateKey();

        String testData = "Jim's test";
        EncryptionResult enc = encrypt(key, testData);

        String restored = decrypt(key, enc.getNonce(), enc.getCipherText());
        System.out.println(restored);   // --> Jim's test
    }

    /* ---------------------------------------------------------------------- */
    /*                      Helper: register BC provider                      */
    /* ---------------------------------------------------------------------- */

    private static void ensureBouncyCastle() {
        if (Security.getProvider("BCFIPS") == null) {
            Security.addProvider(new BouncyCastleFipsProvider());
        }
    }
}