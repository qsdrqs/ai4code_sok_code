import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * A utility class for ECDSA signing operations.
 */
public class EcdsaSigner {

    /**
     * Signs a message using an ECDSA private key.
     * <p>
     * NOTE: This is a direct translation of the provided Python code, which
     * ignores the 'message' parameter and always signs the literal byte string for "message".
     *
     * @param message The message to be signed (this parameter is ignored, as in the original Python code).
     * @param key The ECDSA {@link PrivateKey} to use for signing.
     * @return The raw byte array of the signature in DER format.
     * @throws NoSuchAlgorithmException If the specified signature algorithm is not available.
     * @throws InvalidKeyException If the provided key is invalid.
     * @throws SignatureException If an error occurs during the signing operation.
     */
    public static byte[] signMessage(String message, PrivateKey key)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // The original Python code `key.sign(b"message")` ignores the message parameter
        // and signs the literal byte string "message". We replicate that behavior here.
        byte[] dataToSign = "message".getBytes(StandardCharsets.UTF_8);

        // 1. Get an instance of the Signature object for ECDSA.
        // "SHA256withECDSA" is a standard and secure choice.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the signature object with the private key for signing.
        ecdsaSign.initSign(key);

        // 3. Update the signature object with the data to be signed.
        ecdsaSign.update(dataToSign);

        // 4. Generate the signature and return it.
        return ecdsaSign.sign();
    }
}