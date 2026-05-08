import java.security.PrivateKey;
import java.security.Signature;

public class MessageSigner {
    
    /**
     * sign a message
     */
    public static byte[] sign(byte[] msg, PrivateKey privKey) throws Exception {
        // "SHA256withRSA" uses PKCS#1 v1.5 padding by default, 
        // equivalent to Python's PKCS1_v1_5 + SHA256
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privKey);
        signer.update(msg);
        byte[] signature = signer.sign();
        return signature;
    }
}