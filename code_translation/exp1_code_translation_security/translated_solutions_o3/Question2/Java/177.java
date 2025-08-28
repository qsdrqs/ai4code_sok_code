public class CryptoUtils {

    /**
     * Sign a message with the given secret / signing key.
     *
     * @param message the message to sign
     * @param sk      the secret / signing key (must expose a `sign(byte[])` method)
     * @return        the signature produced by the key
     */
    public static byte[] sign(byte[] message, SigningKey sk) {
        return sk.sign(message);
    }
}