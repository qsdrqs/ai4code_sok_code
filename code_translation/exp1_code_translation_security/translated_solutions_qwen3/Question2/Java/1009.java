import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;

public class ECDSATest {
    public static void main(String[] args) throws Exception {
        String messageStr = "hehehe";
        byte[] message = messageStr.getBytes(StandardCharsets.UTF_8);

        // Generate key pair using NIST P-384 (secp384r1)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"));
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Sign the message
        Signature signature = Signature.getInstance("SHA384withECDSA");
        signature.initSign(sk);
        signature.update(message);
        byte[] sig = signature.sign();

        // Verify the signature
        signature.initVerify(vk);
        signature.update(message);
        boolean verified = signature.verify(sig);

        System.out.println(verified);
    }
}