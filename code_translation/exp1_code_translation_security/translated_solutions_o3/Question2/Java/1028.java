/**
 * Utility class that groups common ECDSA-related helpers.
 * <p>
 * NOTE: The concrete ECDSA class (signing / verification key) will be supplied
 * at build time, so here we only rely on the fact that it exposes the two
 * methods shown below:
 *
 *   byte[]  sign(byte[] message)
 *   boolean verify(byte[] message, byte[] signature)
 *
 * Feel free to adapt the parameter/return types if your particular library
 * uses something other than byte[] (e.g., String, BigInteger, etc.).
 */
public final class EcdsaHelper {

    private EcdsaHelper() {
        /* static helpers – no instances allowed */
    }

    /**
     * Signs the provided message with the given ECDSA signing key.
     *
     * @param ecdsaKey the ECDSA signing key
     * @param message  the message to sign
     * @return the resulting signature
     */
    public static byte[] sign(ECDSA ecdsaKey, byte[] message) {
        return ecdsaKey.sign(message);
    }

    /**
     * Verifies a signature with the supplied ECDSA verification key.
     *
     * @param ecdsaKey  the ECDSA verification key
     * @param message   the original message
     * @param signature the signature to verify
     * @return {@code true} if the signature is valid, {@code false} otherwise
     */
    public static boolean verify(ECDSA ecdsaKey, byte[] message, byte[] signature) {
        return ecdsaKey.verify(message, signature);
    }
}