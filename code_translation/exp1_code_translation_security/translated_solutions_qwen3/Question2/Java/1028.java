import java.security.Signature;
import java.security.PublicKey;
import java.security.PrivateKey;

/**
 * Utility class for ECDSA signing and verification operations.
 */
public class ECDSAUtil {

    /**
     * Signs a given message using an ECDSA signing key.
     *
     * @param ECDSA   The ECDSA signing key (PrivateKey).
     * @param message The message to be signed as a byte array.
     * @return The signature as a byte array.
     * @throws Exception If an error occurs during the signing process.
     */
    public static byte[] sign(PrivateKey ECDSA, byte[] message) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ECDSA);
        signature.update(message);
        return signature.sign();
    }

    /**
     * Verifies a given signature using an ECDSA verification key.
     *
     * @param ECDSA     The ECDSA verification key (PublicKey).
     * @param message   The message to be verified as a byte array.
     * @param signature The signature to be verified as a byte array.
     * @return True if the signature is valid, False otherwise.
     * @throws Exception If an error occurs during the verification process.
     */
    public static boolean verify(PublicKey ECDSA, byte[] message, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initVerify(ECDSA);
        sig.update(message);
        return sig.verify(signature);
    }
}