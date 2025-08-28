import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSAExample {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair using P-384 curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("P-384");
        kpg.initialize(ecSpec);
        KeyPair keyPair = kpg.generateKeyPair();

        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        byte[] message = "testing".getBytes(java.nio.charset.StandardCharsets.UTF_8);

        // Sign the message
        byte[] signature = signMessage(sk, message);

        // Verify the signature
        boolean result = verifySignature(vk, message, signature);

        // Output the result
        System.out.println(result);
    }

    /**
     * Signs a message using the provided private key.
     */
    private static byte[] signMessage(PrivateKey sk, byte[] message) throws Exception {
        Signature signature = Signature.getInstance("SHA384withECDSA", "BC");
        signature.initSign(sk);
        signature.update(message);
        return signature.sign();
    }

    /**
     * Verifies a signature using the provided public key.
     */
    private static boolean verifySignature(PublicKey vk, byte[] message, byte[] signature) throws Exception {
        Signature sig = Signature.getInstance("SHA384withECDSA", "BC");
        sig.initVerify(vk);
        sig.update(message);
        return sig.verify(signature);
    }
}