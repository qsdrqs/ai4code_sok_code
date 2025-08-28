import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Direct translation of the given Python snippet that uses the
 * pyaes AES-CTR implementation.  The code below reproduces the
 * very same behaviour with the JDK crypto libraries.
 *
 * NOTE:
 * – In the original Python code no IV / nonce is supplied, so
 *   pyaes starts with an all-zero 128-bit counter.  We do exactly
 *   the same here by passing a 16-byte array filled with zeros.
 * – The key is expected to be 32 bytes (256 bit) long, encoded
 *   in UTF-8, just like in the Python example.
 */
public final class AesCtrDemo {

    /**
     * Encrypts the given plain-text string with AES-256 in CTR mode.
     *
     * @param k 256-bit key (UTF-8 bytes).
     * @param m message to encrypt.
     * @return cipher-text bytes.
     */
    public static byte[] encrypt(byte[] k, String m) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");

        // Same zero IV/nonce that pyaes uses when none is supplied.
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        return cipher.doFinal(m.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts the cipher-text produced by {@link #encrypt}.
     *
     * @param k 256-bit key (UTF-8 bytes).
     * @param ct cipher-text bytes.
     * @return recovered plain-text.
     */
    public static String decrypt(byte[] k, byte[] ct) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");

        byte[] iv = new byte[16];                      // all zeros
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] pt = cipher.doFinal(ct);
        return new String(pt, StandardCharsets.UTF_8);
    }

    /* ---------------- simple test driver ---------------- */

    private static void roundTripTest() throws Exception {
        byte[] k = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);

        byte[] ct = encrypt(
                k,
                "Text may be any length you wish, no padding is required"
        );

        // Prints: Text may be any length you wish, no padding is required
        System.out.println(decrypt(k, ct));
    }

    public static void main(String[] args) throws Exception {
        roundTripTest();
    }
}