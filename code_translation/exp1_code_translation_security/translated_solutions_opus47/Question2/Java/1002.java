import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

public class ECDSASignature {
    
    public static byte[] sign(PrivateKey key, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(key);
        signature.update(data);
        return signature.sign();
    }
    
    public static void test() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp384r1"));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        
        byte[] data = "this is some data I'd like to sign".getBytes();
        System.out.println(Arrays.toString(sign(privateKey, data)));
    }
    
    public static void main(String[] args) throws Exception {
        test();
    }
}