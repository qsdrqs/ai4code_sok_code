import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class ECDSASigner {
    // Function that signs input with the given ECDSA private key
    public static byte[] sign(byte[] message, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }
}