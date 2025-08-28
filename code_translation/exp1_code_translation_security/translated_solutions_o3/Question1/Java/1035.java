import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Python → Java translation of the AES/EAX example.
 *
 * Needs the Bouncy Castle provider (bc-prov-*.jar) on the class-path.
 * All required jars / modules will be supplied by the runner, so the
 * imports below can be used as-is.
 */
public class AesEaxExample {

    /* ------------------------------------------------------------------ */
    /* helper container                                                   */
    /* ------------------------------------------------------------------ */
    private static final class EncResult {
        final byte[] cipherText;
        final byte[] tag;
        final byte[] nonce;

        EncResult(byte[] c, byte[] t, byte[] n) {
            this.cipherText = c;
            this.tag        = t;
            this.nonce      = n;
        }
    }

    /* ------------------------------------------------------------------ */
    /* encrypt / decrypt                                                  */
    /* ------------------------------------------------------------------ */
    private static final SecureRandom RNG = new SecureRandom();
    private static final int TAG_LEN_BITS = 128;                // same as PyCryptodome default
    private static final int TAG_LEN_BYTES = TAG_LEN_BITS / 8;  // = 16

    public static EncResult encrypt(byte[] key, byte[] msg) throws Exception {

        // random 16-byte nonce (PyCryptodome uses 16 bytes by default)
        byte[] nonce = new byte[16];
        RNG.nextBytes(nonce);

        SecretKey sk = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, sk, new AEADParameterSpec(nonce, TAG_LEN_BITS));

        byte[] out = cipher.doFinal(msg);          // ciphertext || tag

        byte[] cText = Arrays.copyOfRange(out, 0, out.length - TAG_LEN_BYTES);
        byte[] tag   = Arrays.copyOfRange(out, out.length - TAG_LEN_BYTES, out.length);

        return new EncResult(cText, tag, nonce);
    }

    public static byte[] decrypt(byte[] key,
                                 byte[] cipherText,
                                 byte[] nonce,
                                 byte[] tag) throws Exception {

        SecretKey sk = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, sk, new AEADParameterSpec(nonce, TAG_LEN_BITS));

        // concat ciphertext || tag (EAX expects them together for decryption)
        byte[] in = new byte[cipherText.length + tag.length];
        System.arraycopy(cipherText, 0, in, 0, cipherText.length);
        System.arraycopy(tag,        0, in, cipherText.length, tag.length);

        // AEADBadTagException will be thrown automatically on a wrong tag
        return cipher.doFinal(in);
    }

    /* ------------------------------------------------------------------ */
    /* demo                                                               */
    /* ------------------------------------------------------------------ */
    public static void main(String[] args) throws Exception {

        // Register Bouncy Castle once per JVM
        Security.addProvider(new BouncyCastleProvider());

        byte[] key = "Sixteen byte key".getBytes(StandardCharsets.UTF_8);

        EncResult enc = encrypt(key, "message".getBytes(StandardCharsets.UTF_8));
        byte[] plain  = decrypt(key, enc.cipherText, enc.nonce, enc.tag);

        System.out.println(new String(plain, StandardCharsets.UTF_8));  // -> message
    }
}