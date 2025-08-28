import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSAExample {
    
    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static byte[] sign(String message, PrivateKey key) throws Exception {
        // Create signature instance using ECDSA with SHA-1 (similar to Python ecdsa default)
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(key);
        signature.update(message.getBytes("UTF-8"));
        return signature.sign();
    }
    
    public static PrivateKey generateSigningKey() throws Exception {
        // Generate key pair using secp192r1 (equivalent to NIST192p)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        return keyPair.getPrivate();
    }
    
    public static void main(String[] args) {
        try {
            PrivateKey signingKey = generateSigningKey();
            byte[] signature = sign("something", signingKey);
            
            // Print the signature (encoded as Base64 for readability)
            System.out.println(Base64.getEncoder().encodeToString(signature));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}