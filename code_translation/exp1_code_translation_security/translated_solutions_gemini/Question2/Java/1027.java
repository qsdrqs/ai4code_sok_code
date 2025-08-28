import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class MessageUtils {

    /**
     * Signs a message using a private key.
     *
     * @param message The message to be signed, as a byte array.
     * @param signingKey The java.security.PrivateKey object to use for signing.
     * @return The resulting signature as a byte array.
     * @throws NoSuchAlgorithmException if the specified algorithm is not available.
     * @throws InvalidKeyException if the signing key is invalid.
     * @throws SignatureException if an error occurs during the signing operation.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // In Java, the signing algorithm must be specified. This algorithm must be
        // compatible with the provided PrivateKey.
        // For an EC key, "SHA256withECDSA" is a common choice.
        // For an RSA key, "SHA256withRSA" would be used.
        String algorithm = "SHA256withECDSA";

        // Get a Signature instance for the specified algorithm
        Signature signature = Signature.getInstance(algorithm);

        // Initialize the Signature instance for signing with the private key
        signature.initSign(signingKey);

        // Add the message data to be signed
        signature.update(message);

        // Generate the signature
        return signature.sign();
    }
}