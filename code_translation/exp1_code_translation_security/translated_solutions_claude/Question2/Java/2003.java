import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ECDSAExample {
    
    public static byte[] sign(String inp, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update(inp.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }
    
    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair (equivalent to SigningKey.generate())
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        
        // Sign the message and print the result
        byte[] signatureBytes = sign("Hello world", sk);
        
        // Convert to Base64 for readable output (similar to Python's default behavior)
        String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
        System.out.println(signatureBase64);
    }
}