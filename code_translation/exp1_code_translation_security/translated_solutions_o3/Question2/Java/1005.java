import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/*
 * NOTE:
 *   The classes `SigningKey` and `VerifyingKey` (together with all the
 *   crypto-related code they rely on) are assumed to be present on the
 *   class-path – exactly like the `ecdsa` module that was available in
 *   the original Python snippet.
 */
public final class EcdsaDemo {

    /**
     * Signs a message and immediately verifies the freshly-created
     * signature (mirrors the Python implementation 1-for-1).
     *
     * @param msg  The message to be signed.
     * @param key  The private/signing key.
     * @return     The DER-encoded ECDSA signature.
     */
    public static byte[] sign(byte[] msg, SigningKey key) {
        // Obtain the matching public key
        VerifyingKey vk = key.getVerifyingKey();

        // Create the signature
        byte[] signature = key.sign(msg);

        // Sanity-check: verify the signature we just produced
        if (!vk.verify(signature, msg)) {
            throw new IllegalStateException("Signature verification failed");
        }

        return signature;
    }

    /**
     * Re-implementation of the Python `test()` helper.
     */
    public static void test() {
        // Generate a new signing (private) key
        SigningKey sk = SigningKey.generate();

        // Sign the literal string "message"
        byte[] sig = sign("message".getBytes(StandardCharsets.UTF_8), sk);

        // Roughly mimics Python’s default `bytes` printing behaviour
        System.out.println(Arrays.toString(sig));
    }

    public static void main(String[] args) {
        test();
    }
}