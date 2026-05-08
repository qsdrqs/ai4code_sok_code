// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/security/Signature.html
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;

public class ECDSASigner {

    public static byte[] signString(ECPrivateKey privateKey, byte[] data)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        // keyPairGenerator.initialize(new ECGenParameterSpec("secp384r1"));
        // KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // byte[] data = "this is some data I'd like to sign".getBytes();
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    // public static void main(String[] args) throws Exception {
    //     KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
    //     keyPairGenerator.initialize(new ECGenParameterSpec("secp384r1"));
    //     KeyPair keyPair = keyPairGenerator.generateKeyPair();
    //     byte[] sig = signString((ECPrivateKey) keyPair.getPrivate(), "hello world".getBytes());
    //     System.out.println(Arrays.toString(sig));
    // }
}