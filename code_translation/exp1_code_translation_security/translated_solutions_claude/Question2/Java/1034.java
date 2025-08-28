import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class EcdsaExample {
    public static void main(String[] args) {
        try {
            // Generate ECDSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
            keyPairGenerator.initialize(ecSpec);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            
            String message = "Test message";
            
            // Sign the message
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(message.getBytes());
            byte[] signatureBytes = signature.sign();
            
            // Verify the signature
            signature.initVerify(publicKey);
            signature.update(message.getBytes());
            boolean isValid = signature.verify(signatureBytes);
            
            System.out.println(isValid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}