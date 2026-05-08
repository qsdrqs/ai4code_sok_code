import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class ECDSAUtils {

    /**
     * Function that signs a given message using a ECDSA signing key.
     *
     * @param ecdsa   ECDSA signing key
     * @param message message to be signed
     * @return signature
     */
    public static byte[] sign(PrivateKey ecdsa, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ecdsa);
        signature.update(message);
        return signature.sign();
    }

    /**
     * Function that verifies a given signature using a ECDSA verification key.
     *
     * @param ecdsa     ECDSA verification key
     * @param message   message to be verified
     * @param signature signature to be verified
     * @return true if signature is valid, false otherwise
     */
    public static boolean verify(PublicKey ecdsa, byte[] message, byte[] signature)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initVerify(ecdsa);
        sig.update(message);
        return sig.verify(signature);
    }
}