import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MessageSigner {
    
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(signingKey);
        signature.update(message);
        return signature.sign();
    }
    
    // Alternative method if you want to work with String messages
    public static byte[] signMessage(String message, PrivateKey signingKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        return signMessage(message.getBytes(), signingKey);
    }
}