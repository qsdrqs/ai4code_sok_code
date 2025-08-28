/*
 *  Translation of the Python code that used
 *  Crypto.Cipher.AES in EAX-mode to Java.
 *
 *  The code below relies on Bouncy Castle (or any other
 *  provider that supports EAX).  All required
 *  dependencies are assumed to be available at build-time.
 */

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public final class AesEaxSample {

    /* ---- constants ---- */
    private static final int    KEY_SIZE_BYTES   = 16;      // 128-bit key
    private static final int    NONCE_SIZE_BYTES = 16;      // 128-bit nonce
    private static final int    TAG_SIZE_BITS    = 128;     // 16-byte tag
    private static final SecureRandom RNG        = new SecureRandom();

    /* ---- small helper container ---- */
    public static final class EncodedData {
        public final byte[] cipherText;   // encrypted payload
        public final byte[] tag;          // authentication tag

        private EncodedData(byte[] cipherText, byte[] tag) {
            this.cipherText = cipherText;
            this.tag        = tag;
        }
    }

    /* ----------------------------------------------------------
     *  Encrypt a UTF-8 string and return ciphertext + tag
     * ---------------------------------------------------------- */
    public static EncodedData encodeString(String message,
                                           byte[] key,
                                           byte[] nonce)
                                           throws InvalidCipherTextException {

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters params =
                new AEADParameters(new KeyParameter(key), TAG_SIZE_BITS, nonce);

        cipher.init(true, params);                 // true => encrypt

        byte[] input  = message.getBytes(StandardCharsets.UTF_8);
        byte[] output = new byte[cipher.getOutputSize(input.length)];

        int outLen = cipher.processBytes(input, 0, input.length, output, 0);
        outLen    += cipher.doFinal(output, outLen);

        int tagLen      = TAG_SIZE_BITS / 8;
        byte[] ctBytes  = new byte[outLen - tagLen];
        byte[] tagBytes = new byte[tagLen];

        System.arraycopy(output, 0,               ctBytes, 0,              ctBytes.length);
        System.arraycopy(output, ctBytes.length, tagBytes, 0,             tagBytes.length);

        return new EncodedData(ctBytes, tagBytes);
    }

    /* ----------------------------------------------------------
     *  Decrypt previously produced EncodedData structure
     * ---------------------------------------------------------- */
    public static String decodeString(EncodedData data,
                                      byte[] key,
                                      byte[] nonce)
                                      throws InvalidCipherTextException {

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters params =
                new AEADParameters(new KeyParameter(key), TAG_SIZE_BITS, nonce);

        cipher.init(false, params);                // false => decrypt

        /* concatenate cipher text and tag, exactly the format
           used when we encrypted */
        byte[] combined = new byte[data.cipherText.length + data.tag.length];
        System.arraycopy(data.cipherText, 0, combined, 0,               data.cipherText.length);
        System.arraycopy(data.tag,        0, combined, data.cipherText.length, data.tag.length);

        byte[] output = new byte[cipher.getOutputSize(combined.length)];
        int outLen    = cipher.processBytes(combined, 0, combined.length, output, 0);
        outLen       += cipher.doFinal(output, outLen);

        return new String(output, 0, outLen, StandardCharsets.UTF_8);
    }

    /* ----------------------------------------------------------
     *                demo / main method
     * ---------------------------------------------------------- */
    public static void main(String[] args) throws Exception {

        String originalMessage = "secret data";

        /* generate random key & nonce */
        byte[] key   = new byte[KEY_SIZE_BYTES];
        byte[] nonce = new byte[NONCE_SIZE_BYTES];
        RNG.nextBytes(key);
        RNG.nextBytes(nonce);

        System.out.println(originalMessage);

        EncodedData cipherText = encodeString(originalMessage, key, nonce);

        String decoded = decodeString(cipherText, key, nonce);
        System.out.println(decoded);
    }
}