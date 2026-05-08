//well there's a java crypto library but that would be cheating
import java.security.KeyPair;
//what
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

public class Main {

    //sample code equivalent to Python PyPI guide:
    //KeyPair sk = generateKeyPair(); // uses secp192r1 (NIST P-192 / NIST192p)
    //PublicKey vk = sk.getPublic();
    //byte[] signature = sign("message".getBytes(), sk);
    //assert verify(vk, signature, "message".getBytes());

    public static byte[] sign(byte[] message, KeyPair key) throws Exception {
        PublicKey vk = key.getPublic();
        Signature signer = Signature.getInstance("SHA1withECDSA");
        signer.initSign(key.getPrivate());
        signer.update(message);
        return signer.sign();
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp192r1")); // NIST192p equivalent
        return kpg.generateKeyPair();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(sign("i am a message".getBytes(), generateKeyPair())));
    }
}