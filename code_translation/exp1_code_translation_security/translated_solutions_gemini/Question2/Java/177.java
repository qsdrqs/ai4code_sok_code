/**
 * This interface represents the secret key dependency ('sk').
 * In a real-world scenario, this would be implemented by a class from a
 * cryptography library that encapsulates a private key and its signing algorithm.
 */
interface SecretKey {
    /**
     * Signs the given message.
     * @param message The message to sign, as a byte array.
     * @return The signature, as a byte array.
     */
    byte[] sign(byte[] message);
}

public class SignatureUtil {

    /**
     * Signs a message using a secret key.
     *
     * @param m  The message to be signed (represented as a byte array).
     * @param sk The secret key object used for signing.
     * @return The resulting signature (as a byte array).
     */
    public static byte[] sign(byte[] m, SecretKey sk) {
        return sk.sign(m);
    }
}