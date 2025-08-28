import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {
    
    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static void myfunction(byte[] message) throws Exception {
        // Generate key pair using SECP256k1 curve
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        
        // Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(sk);
        signature.update(message);
        byte[] sig = signature.sign();
    }
}