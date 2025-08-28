import java.security.PrivateKey;
import java.security.Signature;
import java.nio.charset.StandardCharsets;

public class EcdsaSign {

    /**
     * Signs the fixed message "message" using the provided ECDSA private key.
     * 
     * @param msg  Ignored in this implementation (as in the original Python code).
     * @param key  The ECDSA private key used for signing.
     * @return     The signature in DER-encoded format.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(byte[] msg, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(key);
        signature.update("message".getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }
}