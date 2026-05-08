import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MyClass {
    public static void myFunction(byte[] message) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        
        // Generate signing key using SECP256k1 curve
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        // Sign the message (Python's ecdsa library uses SHA1 by default)
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(keyPair.getPrivate());
        signature.update(message);
        byte[] sig = signature.sign();
    }
}