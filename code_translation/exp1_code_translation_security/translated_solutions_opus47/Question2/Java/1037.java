import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class Main {
    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair on NIST P-384 curve (equivalent to NIST384p)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();
        
        byte[] message = "testing".getBytes(StandardCharsets.UTF_8);
        byte[] signature = signMessage(sk, message);
        
        // Verify the signature
        Signature verifier = Signature.getInstance("SHA384withECDSA");
        verifier.initVerify(vk);
        verifier.update(message);
        System.out.println(verifier.verify(signature));
    }
    
    public static byte[] signMessage(PrivateKey key, byte[] message) throws Exception {
        Signature signer = Signature.getInstance("SHA384withECDSA");
        signer.initSign(key);
        signer.update(message);
        return signer.sign();
    }
}