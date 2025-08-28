import java.security.PrivateKey;
import java.security.Signature;
import java.security.GeneralSecurityException;

public class ECDSASigner {

    /**
     * Signs the given message using the provided ECDSA private key with SHA-256.
     *
     * @param message     The message to be signed (as a byte array).
     * @param privateKey  The ECDSA private key used for signing.
     * @return            The signature as a byte array (DER-encoded ASN.1 format).
     * @throws GeneralSecurityException if an error occurs during the signing process.
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws GeneralSecurityException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }
}