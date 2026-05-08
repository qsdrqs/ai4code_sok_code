import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    public static byte[] sign(String message, PrivateKey key) throws Exception {
        // ecdsa library uses SHA-1 by default with ECDSA
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(key);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Generate an EC key pair using NIST P-192 (equivalent to NIST192p in ecdsa)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1");
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();

        byte[] signature = sign("something", keyPair.getPrivate());
        System.out.println(Arrays.toString(signature));
    }
}