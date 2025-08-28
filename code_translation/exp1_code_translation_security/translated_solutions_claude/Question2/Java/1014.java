import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.nio.charset.StandardCharsets;

/**
 * Functions that signs a given message using a given ECDSA signing key
 */
public class MessageSigner {
    
    /**
     * Signs a given message using a given ECDSA signing key
     * 
     * @param message The message to be signed (as byte array)
     * @param signingKey The ECDSA private key for signing
     * @return The signature in DER format
     * @throws NoSuchAlgorithmException if SHA256withECDSA algorithm is not available
     * @throws InvalidKeyException if the signing key is invalid
     * @throws SignatureException if signing fails
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // Hash the message using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message);
        
        // Create ECDSA signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        
        // Initialize with the private key
        signature.initSign(signingKey);
        
        // Update with the message hash
        signature.update(messageHash);
        
        // Sign and return the signature (DER encoded by default)
        return signature.sign();
    }
    
    /**
     * Convenience method for signing a string message
     * 
     * @param message The message to be signed (as String)
     * @param signingKey The ECDSA private key for signing
     * @return The signature in DER format
     * @throws NoSuchAlgorithmException if SHA256withECDSA algorithm is not available
     * @throws InvalidKeyException if the signing key is invalid
     * @throws SignatureException if signing fails
     */
    public static byte[] signMessage(String message, PrivateKey signingKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return signMessage(message.getBytes(StandardCharsets.UTF_8), signingKey);
    }
}