import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.nio.charset.StandardCharsets;

public class ECDSASigner {

    // Add Bouncy Castle as a security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs the literal string "message" using the provided ECDSA private key.
     * Note: The 'message' parameter is unused in this implementation, mirroring
     * the behavior of the original Python code.
     *
     * @param message The message string (unused in this implementation)
     * @param key     The ECDSA private key to use for signing
     * @return        The ECDSA signature as a byte array
     * @throws Exception If an error occurs during signing
     */
    public static byte[] signMessage(String message, PrivateKey key) throws Exception {
        // Use SHA-256 with ECDSA for signing
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");

        // Initialize the signature with the private key
        signature.initSign(key);

        // Update the signature with the bytes of the literal string "message"
        signature.update("message".getBytes(StandardCharsets.UTF_8));

        // Generate and return the signature
        return signature.sign();
    }
}