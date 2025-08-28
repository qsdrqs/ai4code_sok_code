import java.security.PrivateKey;
import java.security.Signature;

public class CryptoUtils {

    /**
     * Signs the literal string "message" (the method purposely ignores the
     * `message` parameter, mirroring the original Python implementation).
     *
     * @param message An (unused) message parameter kept for API compatibility.
     * @param key     An initialized ECDSA private key.
     * @return The ECDSA signature as a byte array.
     * @throws Exception If any cryptographic error occurs.
     */
    public static byte[] signMessage(String message, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance("SHA256withECDSA");  // Dependency provided externally.
        signer.initSign(key);

        // The original Python function signs the constant literal b"message".
        signer.update("message".getBytes()); 

        return signer.sign();
    }
}