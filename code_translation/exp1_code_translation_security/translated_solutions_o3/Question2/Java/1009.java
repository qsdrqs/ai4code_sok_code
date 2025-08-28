import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class EcdsaExample {

    /**
     * Sign a message with the provided private key.
     */
    private static byte[] sign(PrivateKey sk, byte[] message) throws Exception {
        // SHA1withECDSA is what the Python ecdsa library uses by default
        Signature signer = Signature.getInstance("SHA1withECDSA");
        signer.initSign(sk);
        signer.update(message);
        return signer.sign();
    }

    public static void main(String[] args) throws Exception {
        byte[] message = "hehehe".getBytes("UTF-8");

        // 1. Generate an EC key pair on the NIST P-384 curve (a.k.a. secp384r1)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"));   // NIST-P384
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey sk = kp.getPrivate();
        PublicKey  vk = kp.getPublic();

        // 2. Sign the message
        byte[] signature = sign(sk, message);

        // 3. Verify the signature
        Signature verifier = Signature.getInstance("SHA1withECDSA");
        verifier.initVerify(vk);
        verifier.update(message);
        boolean ok = verifier.verify(signature);

        System.out.println(ok);   // prints: true
    }
}