public class CryptoUtils {

    /**
     * Signs a message using the provided signing key.
     *
     * @param message     The message to sign (e.g., as a byte array).
     * @param signingKey  The key that performs the signature operation.
     * @return            The resulting signature.
     */
    public static byte[] signMessage(byte[] message, SigningKey signingKey) {
        return signingKey.sign(message);
    }
}