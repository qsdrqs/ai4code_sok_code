import java.security.PrivateKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class ECDSASignature {
    
    // Function that signs input with the given ECDSA private key
    public static byte[] sign(byte[] message, PrivateKey privateKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // Create ECDSA signature instance with SHA256
        Signature signature = Signature.getInstance("SHA256withECDSA");
        
        // Initialize with private key
        signature.initSign(privateKey);
        
        // Update with message data
        signature.update(message);
        
        // Generate and return signature
        return signature.sign();
    }
}