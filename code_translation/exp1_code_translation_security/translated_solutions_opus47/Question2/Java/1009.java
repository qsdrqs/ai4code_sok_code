import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    public static byte[] sign(PrivateKey sk, byte[] message) throws Exception {
        Signature signature = Signature.getInstance("SHA384withECDSA");
        signature.initSign(sk);
        signature.update(message);
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        byte[] message = "hehehe".getBytes();

        // Generate a key pair using NIST P-384 (equivalent to NIST384p / secp384r1)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp384r1"));
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        byte[] sig = sign(sk, message);

        // Verify the signature
        Signature verifier = Signature.getInstance("SHA384withECDSA");
        verifier.initVerify(vk);
        verifier.update(message);
        System.out.println(verifier.verify(sig));
    }
}