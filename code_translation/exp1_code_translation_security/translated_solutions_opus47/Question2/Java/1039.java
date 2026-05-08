import java.security.PrivateKey;
import java.security.Signature;

public class SignatureUtil {
    public static byte[] signMessage(String message, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(key);
        signature.update("message".getBytes());
        return signature.sign();
    }
}