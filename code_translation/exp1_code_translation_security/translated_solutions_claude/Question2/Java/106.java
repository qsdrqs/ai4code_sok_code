import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class ECDSAExample {
    
    /**
     * Signs a given message using a given ECDSA signing key
     * 
     * @param message input message
     * @param key ECDSA private key used to sign the message
     * @return signed message
     * @throws Exception if signing fails
     */
    public static byte[] sign(byte[] message, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update(message);
        return signature.sign();
    }
    
    public static void main(String[] args) {
        try {
            // Generate ECDSA key pair using secp256k1 curve
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);
            KeyPair keyPair = keyGen.generateKeyPair();
            
            PrivateKey sk = keyPair.getPrivate();
            PublicKey vk = keyPair.getPublic();
            
            // Sign the message
            byte[] message = "helloworld".getBytes();
            byte[] signedMessage = sign(message, sk);
            
            // Verify the signature
            Signature verifier = Signature.getInstance("SHA256withECDSA");
            verifier.initVerify(vk);
            verifier.update(message);
            boolean isValid = verifier.verify(signedMessage);
            
            System.out.println(isValid);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}