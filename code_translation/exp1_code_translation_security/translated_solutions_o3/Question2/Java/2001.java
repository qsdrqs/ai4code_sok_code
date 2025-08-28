import java.security.PrivateKey;
import java.security.Signature;

public class ECDSASigner {

    /**
     * Signs the given message with the provided ECDSA private key (SHA-256 digest).
     *
     * @param message     The data to sign.
     * @param privateKey  An initialized EC private key.
     * @return The DER-encoded ECDSA signature.
     * @throws Exception  If the signing operation fails.
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws Exception {
        // "SHA256withECDSA" == ECDSA over the SHA-256 hash
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(privateKey);
        ecdsa.update(message);
        return ecdsa.sign();
    }
}