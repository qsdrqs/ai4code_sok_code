import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * A utility class for performing ECDSA signing and verification.
 * This class provides static methods that correspond to the Python functions.
 */
public class EcdsaUtils {

    // A common and secure algorithm for ECDSA. The Python code did not specify
    // one, so we choose a standard.
    private static final String SIGNING_ALGORITHM = "SHA256withECDSA";

    /**
     * Function that signs a given message using an ECDSA signing key.
     *
     * @param privateKey The ECDSA private key (equivalent to Python's ECDSA signing key object).
     * @param message    The message to be signed as a byte array.
     * @return The signature as a byte array.
     * @throws RuntimeException if any cryptographic error occurs during signing.
     */
    public static byte[] sign(PrivateKey privateKey, byte[] message) {
        try {
            // Create a Signature object and initialize it with the private key
            Signature ecdsaSign = Signature.getInstance(SIGNING_ALGORITHM);
            ecdsaSign.initSign(privateKey);

            // Add the message data to be signed
            ecdsaSign.update(message);

            // Generate the signature
            return ecdsaSign.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // Wrap checked exceptions in a RuntimeException for simplicity,
            // as the original Python code does not show explicit exception handling.
            throw new RuntimeException("Error occurred while signing the message.", e);
        }
    }

    /**
     * Function that verifies a given signature using an ECDSA verification key.
     *
     * @param publicKey The ECDSA public key (equivalent to Python's ECDSA verification key object).
     * @param message   The message to be verified as a byte array.
     * @param signature The signature to be verified as a byte array.
     * @return True if the signature is valid, False otherwise.
     * @throws RuntimeException if any cryptographic error occurs during verification.
     */
    public static boolean verify(PublicKey publicKey, byte[] message, byte[] signature) {
        try {
            // Create a Signature object and initialize it with the public key
            Signature ecdsaVerify = Signature.getInstance(SIGNING_ALGORITHM);
            ecdsaVerify.initVerify(publicKey);

            // Add the message data to be verified
            ecdsaVerify.update(message);

            // Verify the signature
            return ecdsaVerify.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            // An exception here usually indicates a problem with the key or signature format,
            // not a simple verification failure (which returns false).
            throw new RuntimeException("Error occurred while verifying the signature.", e);
        }
    }
}