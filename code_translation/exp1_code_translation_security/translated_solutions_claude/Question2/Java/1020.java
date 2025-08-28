import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.bouncycastle.jce.ECNamedCurveTable;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {
    private static KeyPair keyPair;
    private static PublicKey vk;
    
    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
        
        try {
            // Generate SECP256k1 key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            keyPair = keyGen.generateKeyPair();
            vk = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ECDSA keys", e);
        }
    }
    
    public static boolean signMessage(PrivateKey sk, String message) {
        try {
            // Sign the message
            Signature signer = Signature.getInstance("SHA256withECDSA", "BC");
            signer.initSign(sk);
            signer.update(message.getBytes("UTF-8"));
            byte[] signature = signer.sign();
            
            // Verify the signature
            Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
            verifier.initVerify(vk);
            verifier.update(message.getBytes("UTF-8"));
            return verifier.verify(signature);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(signMessage(keyPair.getPrivate(), ".."));
    }
}