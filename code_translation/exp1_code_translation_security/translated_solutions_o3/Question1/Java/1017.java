import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/**
 * Helper class that mimics the two Python functions encrypt() / decrypt().
 */
public final class AesEaxUtil {

    /** Container that corresponds to the (nonce, tag, ciphertext) tuple. */
    public static final class CipherTuple {
        public final byte[] nonce;
        public final byte[] tag;
        public final byte[] ciphertext;

        public CipherTuple(byte[] nonce, byte[] tag, byte[] ciphertext) {
            this.nonce = nonce;
            this.tag = tag;
            this.ciphertext = ciphertext;
        }
    }

    private static final int NONCE_LEN = 16;        // 128-bit nonce
    private static final int TAG_LEN   = 16;        // 128-bit authentication tag
    private static final SecureRandom RNG = new SecureRandom();

    private AesEaxUtil() { /* utility class – do not instantiate */ }

    /**
     * Python equivalent:
     *     nonce, tag, ciphertext = encrypt(key, plaintext)
     */
    public static CipherTuple encrypt(byte[] key, byte[] plaintext)
            throws InvalidCipherTextException {

        // ---------------------------------------------------------------------
        // 1.  Build cipher instance: AES in EAX mode
        // ---------------------------------------------------------------------
        EAXBlockCipher eax = new EAXBlockCipher(new AESEngine());

        // ---------------------------------------------------------------------
        // 2.  Create fresh random nonce
        // ---------------------------------------------------------------------
        byte[] nonce = new byte[NONCE_LEN];
        RNG.nextBytes(nonce);

        // ---------------------------------------------------------------------
        // 3.  Initialise cipher for encryption
        // ---------------------------------------------------------------------
        KeyParameter          keyParam  = new KeyParameter(key);
        ParametersWithIV      params    = new ParametersWithIV(keyParam, nonce);
        eax.init(true, params);                               // true = encrypt

        // ---------------------------------------------------------------------
        // 4.  Run encryption
        // ---------------------------------------------------------------------
        byte[] outBuf = new byte[eax.getOutputSize(plaintext.length)];
        int    outLen = eax.processBytes(plaintext, 0, plaintext.length,
                                         outBuf,     0);
        outLen += eax.doFinal(outBuf, outLen);

        // outBuf now contains  [ciphertext || tag]   (tag at the very end)
        int    cipherLen = outLen - TAG_LEN;
        byte[] ciphertext = Arrays.copyOfRange(outBuf, 0,          cipherLen);
        byte[] tag        = Arrays.copyOfRange(outBuf, cipherLen, outLen);

        return new CipherTuple(nonce, tag, ciphertext);
    }

    /**
     * Python equivalent:
     *     plaintext = decrypt(key, (nonce, tag, ciphertext))
     */
    public static byte[] decrypt(byte[] key, CipherTuple tuple)
            throws InvalidCipherTextException {

        // ---------------------------------------------------------------------
        // 1.  Build cipher instance: AES in EAX mode
        // ---------------------------------------------------------------------
        EAXBlockCipher eax = new EAXBlockCipher(new AESEngine());

        // ---------------------------------------------------------------------
        // 2.  Combine key + nonce into cipher parameters
        // ---------------------------------------------------------------------
        KeyParameter          keyParam = new KeyParameter(key);
        ParametersWithIV      params   = new ParametersWithIV(keyParam,
                                                              tuple.nonce);
        eax.init(false, params);                              // false = decrypt

        // ---------------------------------------------------------------------
        // 3.  The Java API expects   [ciphertext || tag]
        // ---------------------------------------------------------------------
        byte[] combined = new byte[tuple.ciphertext.length + tuple.tag.length];
        System.arraycopy(tuple.ciphertext, 0,
                         combined,          0,
                         tuple.ciphertext.length);
        System.arraycopy(tuple.tag, 0,
                         combined, tuple.ciphertext.length,
                         tuple.tag.length);

        byte[] outBuf = new byte[eax.getOutputSize(combined.length)];
        int    outLen = eax.processBytes(combined, 0, combined.length,
                                         outBuf,     0);
        outLen += eax.doFinal(outBuf, outLen);   // authentication is verified
                                                // here – an exception is thrown
                                                // if the tag does not match.

        return Arrays.copyOf(outBuf, outLen);
    }
}