import java.security.PrivateKey;
import java.security.Signature;

public class ECDSAUtil {

    /**
     * Signs a message using an EC private key and SHA-256 with ECDSA.
     *
     * @param key     the EC private key
     * @param message the data to sign
     * @return the signature bytes
     * @throws Exception if the signing operation fails
     */
    public static byte[] signUsingECDSA(PrivateKey key, byte[] message) throws Exception {
        Signature signer = Signature.getInstance("SHA256withECDSA");  // SHA-256 + ECDSA
        signer.initSign(key);                                         // initialize with private key
        signer.update(message);                                       // feed message
        return signer.sign();                                         // produce signature
    }
}