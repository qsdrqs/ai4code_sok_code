import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.SecureRandom;

public class ECDSAExample {
    
    public static byte[] sign(PrivateKey key, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update(data);
        return signature.sign();
    }
    
    public static void test() throws Exception {
        // Generate SECP384R1 private key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        
        byte[] data = "this is some data I'd like to sign".getBytes();
        
        byte[] signatureBytes = sign(privateKey, data);
        
        // Print the signature as hex string (similar to Python's print behavior)
        StringBuilder hexString = new StringBuilder();
        for (byte b : signatureBytes) {
            hexString.append(String.format("%02x", b));
        }
        System.out.println(hexString.toString());
    }
    
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}