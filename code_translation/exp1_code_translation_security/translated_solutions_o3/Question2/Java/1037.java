import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * Java translation of the Python sample:
 *
 * from ecdsa import SigningKey, NIST384p
 * …
 */
public class ECDSADemo {

    public static void main(String[] args) throws Exception {
        /*  If you’re using Bouncy Castle, make sure the provider is registered.
            (The provider jar will be supplied for you, per the instructions.)     */
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // 1. Generate an EC key-pair on the NIST P-384 curve (a.k.a. secp384r1)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(new ECGenParameterSpec("P-384"));
        KeyPair kp = kpg.generateKeyPair();

        PrivateKey sk = kp.getPrivate();   // signing key
        PublicKey  vk = kp.getPublic();    // verifying key

        // 2. Sign the message “testing”
        byte[] message   = "testing".getBytes(StandardCharsets.UTF_8);
        byte[] signature = signMessage(sk, message);

        // 3. Verify the signature
        Signature verifier = Signature.getInstance("SHA384withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update(message);
        boolean ok = verifier.verify(signature);

        System.out.println(ok);            // prints true when everything is correct
    }

    private static byte[] signMessage(PrivateKey key, byte[] message) throws Exception {
        Signature signer = Signature.getInstance("SHA384withECDSA", "BC");
        signer.initSign(key);
        signer.update(message);
        return signer.sign();
    }
}