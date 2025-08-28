import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignatureUtil {
    
    public static byte[] sign(byte[] m, PrivateKey sk) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance("SHA256withRSA"); // or appropriate algorithm
        signature.initSign(sk);
        signature.update(m);
        return signature.sign();
    }
}