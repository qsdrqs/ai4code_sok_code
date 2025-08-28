import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class ECDSAExample {
    
    public static void main(String[] args) throws Exception {
        // Generate key pair using NIST P-192 curve (secp192r1)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1"); // NIST P-192
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();
        
        // Test the signing and verification
        byte[] message = "message".getBytes();
        byte[] signature = sign(message, sk);
        
        boolean isValid = verify(signature, message, vk);
        assert isValid : "Signature verification failed";
        
        System.out.println("Signature verification: " + isValid);
    }
    
    public static byte[] sign(byte[] msg, PrivateKey sk) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(sk);
        signature.update(msg);
        return signature.sign();
    }
    
    public static boolean verify(byte[] signature, byte[] msg, PublicKey vk) throws Exception {
        Signature verifier = Signature.getInstance("SHA1withECDSA");
        verifier.initVerify(vk);
        verifier.update(msg);
        return verifier.verify(signature);
    }
}