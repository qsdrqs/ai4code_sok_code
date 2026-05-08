import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    public static byte[] sign(PrivateKey sk, String m) throws Exception {
        Signature signer = Signature.getInstance("SHA1withECDSA");
        signer.initSign(sk);
        signer.update(m.getBytes(StandardCharsets.UTF_8));
        return signer.sign();
    }

    public static void test() throws Exception {
        // SigningKey.generate() - default curve in python-ecdsa is NIST192p (secp192r1)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1");
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        byte[] signature = sign(sk, "hello world");

        // vk.verify(signature, "hello worfld".encode('utf-8'))
        Signature verifier = Signature.getInstance("SHA1withECDSA");
        verifier.initVerify(vk);
        verifier.update("hello worfld".getBytes(StandardCharsets.UTF_8));
        System.out.println(verifier.verify(signature));
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}