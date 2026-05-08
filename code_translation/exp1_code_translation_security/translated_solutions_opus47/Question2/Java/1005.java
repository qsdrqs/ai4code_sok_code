import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

public class EcdsaExample {

    public static byte[] sign(byte[] msg, KeyPair key) throws Exception {
        PublicKey vk = key.getPublic();

        Signature signer = Signature.getInstance("SHA1withECDSA");
        signer.initSign(key.getPrivate());
        signer.update(msg);
        byte[] signature = signer.sign();

        Signature verifier = Signature.getInstance("SHA1withECDSA");
        verifier.initVerify(vk);
        verifier.update(msg);
        assert verifier.verify(signature);

        return signature;
    }

    public static void test() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp192r1")); // NIST192p
        KeyPair sk = kpg.generateKeyPair();
        System.out.println(Arrays.toString(sign("message".getBytes(), sk)));
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}