import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Function in Java that signs a given message using a given ECDSA signing key
 */
public static byte[] signMessage(byte[] message, PrivateKey signingKey) 
        throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    
    // Create ECDSA signature instance
    Signature signature = Signature.getInstance("SHA256withECDSA");
    
    // Initialize the signature with the private key
    signature.initSign(signingKey);
    
    // Sign the message using the signing key
    signature.update(message);
    byte[] signatureBytes = signature.doFinal();
    
    // Return the signature
    return signatureBytes;
}