import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSASignatureExample {

    public static void main(String[] args) {
        try {
            // Add Bouncy Castle as a Security Provider
            Security.addProvider(new BouncyCastleProvider());

            // Generate ECDSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();

            // Sign the message
            String message = "Hello world";
            byte[] signature = sign(message, keyPair.getPrivate());

            // Print the signature in Base64 format
            System.out.println(Base64.getEncoder().encodeToString(signature));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message.getBytes("UTF-8"));
        return ecdsaSign.sign();
    }
}