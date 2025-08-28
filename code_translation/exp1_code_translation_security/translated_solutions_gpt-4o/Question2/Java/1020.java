import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSASignatureExample {

    public static void main(String[] args) throws Exception {
        // Add BouncyCastle as a Security Provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate ECDSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Message to sign
        String message = "..";

        // Sign the message
        byte[] signature = signMessage(privateKey, message.getBytes());

        // Verify the signature
        boolean isVerified = verifySignature(publicKey, message.getBytes(), signature);

        System.out.println("Signature verified: " + isVerified);
    }

    public static byte[] signMessage(PrivateKey privateKey, byte[] message) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static boolean verifySignature(PublicKey publicKey, byte[] message, byte[] signature) throws Exception {
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }
}