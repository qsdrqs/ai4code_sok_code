import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class ECDSASigner {

    /**
     * Signs a message using the ECDSA algorithm with SHA-256 as the hash function.
     *
     * @param key     The private key used for signing.
     * @param message The message to be signed, as a byte array.
     * @return        The digital signature as a byte array.
     * @throws SignatureException If an error occurs during the signing process.
     */
    public static byte[] signUsingECDSA(PrivateKey key, byte[] message) throws SignatureException {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(key);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            throw new SignatureException("Error during ECDSA signing", e);
        }
    }
}