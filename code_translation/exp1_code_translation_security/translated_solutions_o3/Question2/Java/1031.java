import java.security.PrivateKey;
import java.security.Signature;

/**
 * Sign a message using RSA-PKCS#1 v1.5 with SHA-256.
 *
 * @param msg       message to be signed (raw bytes)
 * @param privKey   RSA private key
 * @return          signature bytes
 * @throws Exception any cryptographic error
 */
public class RsaSigner {

    public static byte[] sign(byte[] msg, PrivateKey privKey) throws Exception {
        // Java’s “SHA256withRSA” = SHA-256 digest + PKCS#1 v1.5 padding
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privKey);
        signer.update(msg);
        return signer.sign();
    }
}