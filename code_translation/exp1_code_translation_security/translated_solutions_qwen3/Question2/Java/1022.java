import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.nio.charset.StandardCharsets;

public class EcdsaExample {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate ECDSA Key Pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256); // NIST P-256 curve
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Step 2: Prepare the message
        String message = "My test message";
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // Step 3: Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(messageBytes);
        byte[] signatureBytes = signature.sign();

        // Step 4: Verify the signature
        Signature verifySignature = Signature.getInstance("SHA256withECDSA");
        verifySignature.initVerify(publicKey);
        verifySignature.update(messageBytes);
        boolean isValid = verifySignature.verify(signatureBytes);

        // Step 5: Output result
        System.out.println(isValid);
    }
}