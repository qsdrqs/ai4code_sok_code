import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    public static byte[] sign(byte[] msg, PrivateKey sk) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(sk);
        signature.update(msg);
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp192r1")); // NIST192p
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        byte[] msg = "message".getBytes();
        byte[] sig = sign(msg, sk);

        Signature verifier = Signature.getInstance("SHA1withECDSA");
        verifier.initVerify(vk);
        verifier.update(msg);
        assert verifier.verify(sig);
    }
}