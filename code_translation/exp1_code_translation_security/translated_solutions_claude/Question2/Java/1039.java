import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MessageSigner {
    
    public static byte[] signMessage(String message, PrivateKey key) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // Create a Signature object for ECDSA with SHA256
        Signature signature = Signature.getInstance("SHA256withECDSA");
        
        // Initialize the signature with the private key
        signature.initSign(key);
        
        // Note: The original Python code signs the literal string "message"
        // regardless of the message parameter. Maintaining this behavior:
        signature.update("message".getBytes());
        
        // Generate and return the signature
        return signature.sign();
    }
}