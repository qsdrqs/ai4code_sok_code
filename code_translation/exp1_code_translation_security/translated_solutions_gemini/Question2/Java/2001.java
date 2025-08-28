import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * A utility class for cryptographic signing operations.
 */
public class EcdsaSigner {

    /**
     * Signs a message using the given ECDSA private key.
     *
     * This method uses the "SHA256withECDSA" algorithm, which is the Java
     * standard equivalent to Python's `ec.ECDSA(hashes.SHA256())`.
     *
     * @param message The byte array representation of the message to be signed.
     * @param privateKey The private key to use for signing. This should be an instance
     *                   of a class that implements java.security.PrivateKey, such as
     *                   java.security.interfaces.ECPrivateKey.
     * @return The resulting signature as a byte array (in ASN.1 DER format).
     * @throws NoSuchAlgorithmException if the "SHA256withECDSA" algorithm is not available.
     * @throws InvalidKeyException      if the provided private key is invalid.
     * @throws SignatureException       if an error occurs during the signing process.
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 1. Get an instance of the Signature object for the "SHA256withECDSA" algorithm.
        Signature signature = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the Signature object for signing with the provided private key.
        signature.initSign(privateKey);

        // 3. Pass the message data to the Signature object.
        signature.update(message);

        // 4. Generate and return the signature.
        return signature.sign();
    }
}