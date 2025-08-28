import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class MessageSigner {
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA"); // or another algorithm
        signature.initSign(signingKey);
        signature.update(message);
        return signature.sign();
    }
}