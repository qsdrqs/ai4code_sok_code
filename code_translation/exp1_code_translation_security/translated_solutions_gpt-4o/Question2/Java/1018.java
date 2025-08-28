import java.security.PrivateKey;
import java.security.Signature;

public class ECDSASignature {

    public static byte[] sign(String msg, PrivateKey key) throws Exception {
        // Create a Signature instance for ECDSA
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        
        // Initialize the signature with the private key
        ecdsa.initSign(key);
        
        // Supply the message to be signed
        ecdsa.update(msg.getBytes());
        
        // Generate the signature
        return ecdsa.sign();
    }
}