import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class EcdsaSample {

    /**
     * Signs the supplied message with the given private (signing) key.
     */
    public static byte[] sign(PrivateKey sk, String message) throws GeneralSecurityException {
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(sk);
        signer.update(message.getBytes(StandardCharsets.UTF_8));
        return signer.sign();
    }

    /**
     * Rough equivalent of the Python test() function.
     */
    public static void test() throws GeneralSecurityException {
        /* Generate an EC key-pair (ECDSA, NIST P-256 / secp256r1). */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp256r1"));   // aka prime256v1
        KeyPair kp = kpg.generateKeyPair();

        /* Sign the correct message. */
        byte[] signature = sign(kp.getPrivate(), "hello world");

        /* Attempt to verify a *different* message to demonstrate failure. */
        Signature verifier = Signature.getInstance("SHA256withECDSA");
        verifier.initVerify(kp.getPublic());
        verifier.update("hello worfld".getBytes(StandardCharsets.UTF_8));  // note the typo
        boolean verified = verifier.verify(signature);

        System.out.println(verified);   // expected output: false
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}