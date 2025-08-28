import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class EcdsaSigner {

    /**
     * Signs the given data using the provided ECDSA private key with SHA-256.
     *
     * @param privateKey The ECDSA private key to use for signing.
     * @param data       The data to be signed as a byte array.
     * @return The DER-encoded ECDSA signature as a byte array.
     * @throws NoSuchAlgorithmException if the SHA256withECDSA algorithm is not available.
     * @throws InvalidKeyException      if the provided key is invalid.
     * @throws SignatureException       if the signing process fails.
     */
    public static byte[] signString(ECPrivateKey privateKey, byte[] data)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // Get an instance of the ECDSA signature engine with SHA-256
        Signature signature = Signature.getInstance("SHA256withECDSA");

        // Initialize the signature engine with the private key
        signature.initSign(privateKey);

        // Update the signature engine with the data to be signed
        signature.update(data);

        // Generate and return the signature
        return signature.sign();
    }
}