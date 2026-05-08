import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;

public class EcdsaSigner {

    public static byte[] sign(String msg, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(key);
        signature.update("message".getBytes(StandardCharsets.UTF_8));
        byte[] sig = signature.sign();
        return sig;
    }
}