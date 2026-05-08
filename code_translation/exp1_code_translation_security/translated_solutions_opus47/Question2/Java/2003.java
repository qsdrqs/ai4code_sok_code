import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static byte[] sign(String inp, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(key);
        signature.update(inp.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }
    
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp192r1"));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        System.out.println(Arrays.toString(sign("Hello world", sk)));
    }
}