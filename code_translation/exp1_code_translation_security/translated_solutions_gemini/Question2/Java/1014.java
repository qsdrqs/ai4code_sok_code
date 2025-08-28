import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Contains functions that sign a given message using a given ECDSA signing key.
 */
public class EcdsaSigner {

    /**
     * Signs a given message using a given ECDSA signing key.
     * <p>
     * This method replicates the Python behavior by first hashing the message with SHA-256
     * and then signing the resulting hash digest.
     *
     * @param message The raw message data to be signed.
     * @param signingKey The ECDSA private key (implementing java.security.PrivateKey) to sign with.
     * @return The signature in ASN.1 DER format as a byte array.
     * @throws NoSuchAlgorithmException if a required cryptographic algorithm is not available.
     * @throws InvalidKeyException if the provided key is invalid.
     * @throws SignatureException if an error occurs during the signing process.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        // 1. Hash the message using SHA-256
        // This corresponds to Python's: hashlib.sha256(message).digest()
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message);

        // 2. Get a Signature instance for ECDSA.
        // We use "NoneWithECDSA" because we are providing the pre-computed hash ourselves,
        // not the raw message.
        Signature ecdsaSign = Signature.getInstance("NoneWithECDSA");

        // 3. Initialize the signature for signing with the private key
        ecdsaSign.initSign(signingKey);

        // 4. Update the signature object with the message HASH to be signed
        // This corresponds to Python's: signing_key.sign_digest(message_hash, ...)
        ecdsaSign.update(messageHash);

        // 5. Generate the signature
        // The JCA ECDSA implementation returns the signature in DER format by default,
        // which corresponds to Python's: sigencode=ecdsa.util.sigencode_der
        return ecdsaSign.sign();
    }
}