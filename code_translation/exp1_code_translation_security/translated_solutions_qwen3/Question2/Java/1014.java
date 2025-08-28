import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.Security;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class Signer {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a given message using a given ECDSA private key.
     * The signature is encoded in DER format, matching the Python ecdsa library.
     *
     * @param message The message to be signed (raw bytes).
     * @param signingKey The ECDSA private key used for signing.
     * @return The DER-encoded ECDSA signature.
     * @throws NoSuchAlgorithmException If the SHA256withECDSA algorithm is not available.
     * @throws InvalidKeyException If the provided key is not a valid ECDSA private key.
     * @throws SignatureException If an error occurs during the signing process.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // Use Bouncy Castle's implementation of ECDSA with SHA-256
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");

        // Initialize the signature with the private key
        signature.initSign(signingKey);

        // Update the signature with the message bytes
        signature.update(message);

        // Generate and return the DER-encoded signature
        return signature.sign();
    }
}