import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;

public class ECDSASignature {
    
    public static byte[] sign(String msg, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update("message".getBytes(StandardCharsets.UTF_8));
        byte[] sig = signature.sign();
        return sig;
    }
    
    // Helper method to generate an ECDSA key pair for testing
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyGen.initialize(ecSpec);
        return keyGen.generateKeyPair();
    }
    
    // Example usage
    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            
            byte[] signature = sign("test message", privateKey);
            System.out.println("Signature generated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}