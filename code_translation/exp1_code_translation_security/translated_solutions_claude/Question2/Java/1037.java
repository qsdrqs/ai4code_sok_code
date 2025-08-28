import java.security.*;
import java.security.spec.*;

public class ECDSAExample {
    
    public static void main(String[] args) {
        try {
            // Generate key pair using NIST P-384 curve (secp384r1)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
            keyGen.initialize(ecSpec);
            KeyPair keyPair = keyGen.generateKeyPair();
            
            PrivateKey sk = keyPair.getPrivate();
            PublicKey vk = keyPair.getPublic();
            
            byte[] message = "testing".getBytes();
            byte[] signature = signMessage(sk, message);
            
            System.out.println(verifySignature(vk, signature, message));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static byte[] signMessage(PrivateKey key, byte[] message) {
        try {
            Signature sig = Signature.getInstance("SHA256withECDSA");
            sig.initSign(key);
            sig.update(message);
            return sig.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean verifySignature(PublicKey key, byte[] signature, byte[] message) {
        try {
            Signature sig = Signature.getInstance("SHA256withECDSA");
            sig.initVerify(key);
            sig.update(message);
            return sig.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}