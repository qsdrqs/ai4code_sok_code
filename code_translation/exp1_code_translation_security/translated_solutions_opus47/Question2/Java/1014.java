import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;

/**
 * Functions that signs a given message using a given ECDSA signing key
 */
public class MessageSigner {

    /**
     * Signs a given message using a given ECDSA signing key
     */
    public static byte[] signMessage(byte[] message, ECPrivateKey signingKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Hash the message
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message);

        // Sign the message hash (Java's ECDSA implementation produces DER-encoded signatures by default)
        Signature signature = Signature.getInstance("NONEwithECDSA");
        signature.initSign(signingKey);
        signature.update(messageHash);
        byte[] signatureBytes = signature.sign();

        return signatureBytes;
    }
}