import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.InvalidKeyException;

public class Signer {
    public byte[] sign(byte[] message, PrivateKey privateKey) throws SignatureException, InvalidKeyException {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA"); // Example algorithm
            signature.initSign(privateKey);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Signing failed", e);
        }
    }
}