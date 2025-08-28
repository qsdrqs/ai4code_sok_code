import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {
    
    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static byte[] sign(PrivateKey sk, byte[] message) throws Exception {
        // Get the verifying key (public key) - not used in this method but matches Python structure
        // PublicKey vk = getPublicKeyFromPrivate(sk);
        
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(sk);
        signature.update(message);
        return signature.sign();
    }
    
    public static void main(String[] args) throws Exception {
        byte[] message = "hehehe".getBytes();
        
        // Generate key pair using NIST P-384 curve (equivalent to NIST384p)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1"); // NIST P-384
        keyGen.initialize(ecSpec, new SecureRandom());
        
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();
        
        byte[] sig = sign(sk, message);
        
        // Verify the signature
        Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update(message);
        boolean isValid = verifier.verify(sig);
        
        System.out.println(isValid);
    }
}