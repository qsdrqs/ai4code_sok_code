import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSASigner {

    public static byte[] signMessage(byte[] message, String key) throws Exception {
        // Generate a random private key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(key);
        keyPairGenerator.initialize(ecSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey signingKey = keyPair.getPrivate();

        // Get the public key
        PublicKey verifyingKey = keyPair.getPublic();

        // Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(signingKey);
        signature.update(message);
        byte[] signatureBytes = signature.sign();

        return signatureBytes;
    }
}