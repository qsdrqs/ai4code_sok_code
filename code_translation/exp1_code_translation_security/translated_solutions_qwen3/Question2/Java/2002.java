import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using the provided private key.
     *
     * @param sk      The private key.
     * @param m       The message to sign.
     * @return        The DER-encoded signature bytes.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(PrivateKey sk, String m) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(sk);
        signature.update(m.getBytes("UTF-8"));
        return signature.sign();
    }

    /**
     * Verifies a signature against a message using the provided public key.
     *
     * @param vk        The public key.
     * @param signature The DER-encoded signature bytes.
     * @param message   The message to verify.
     * @return          True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    public static boolean verify(PublicKey vk, byte[] signature, String message) throws Exception {
        Signature sig = Signature.getInstance("SHA1withECDSA", "BC");
        sig.initVerify(vk);
        sig.update(message.getBytes("UTF-8"));
        return sig.verify(signature);
    }

    /**
     * Test function to generate keys, sign a message, and verify it against a different message.
     *
     * @throws Exception If an error occurs during key generation or signing/verification.
     */
    public static void test() throws Exception {
        // Generate ECDSA key pair using secp192r1 curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(new ECGenParameterSpec("secp192r1"), new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Sign "hello world"
        byte[] signature = sign(sk, "hello world");

        // Try to verify against "hello worfld" (intentional typo)
        boolean verified = verify(vk, signature, "hello worfld");

        // Output the result (should be false)
        System.out.println(verified);
    }

    /**
     * Main method to run the test.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception If an error occurs during execution.
     */
    public static void main(String[] args) throws Exception {
        test();
    }
}