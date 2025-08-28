import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ECDSAUtils {
    
    /**
     * Function that signs a given message using a ECDSA signing key.
     * 
     * @param ecdsaPrivateKey ECDSA signing key
     * @param message message to be signed
     * @return signature
     * @throws SignatureException if signing fails
     * @throws InvalidKeyException if the key is invalid
     * @throws NoSuchAlgorithmException if ECDSA algorithm is not available
     */
    public static byte[] sign(PrivateKey ecdsaPrivateKey, byte[] message) 
            throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ecdsaPrivateKey);
        signature.update(message);
        return signature.sign();
    }
    
    /**
     * Function that verifies a given signature using a ECDSA verification key.
     * 
     * @param ecdsaPublicKey ECDSA verification key
     * @param message message to be verified
     * @param signatureBytes signature to be verified
     * @return true if signature is valid, false otherwise
     * @throws SignatureException if verification fails
     * @throws InvalidKeyException if the key is invalid
     * @throws NoSuchAlgorithmException if ECDSA algorithm is not available
     */
    public static boolean verify(PublicKey ecdsaPublicKey, byte[] message, byte[] signatureBytes) 
            throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(ecdsaPublicKey);
        signature.update(message);
        return signature.verify(signatureBytes);
    }
}