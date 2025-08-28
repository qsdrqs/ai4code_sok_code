import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair using the secp192r1 curve (NIST192p)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(new ECGenParameterSpec("secp192r1"), new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Message to be signed
        byte[] message = "message".getBytes("UTF-8");

        // Sign the message
        byte[] signature = sign(message, sk);

        // Verify the signature
        boolean isValid = verify(message, signature, vk);
        assert isValid : "Signature verification failed!";
    }

    /**
     * Signs a message using ECDSA with SHA-1.
     */
    public static byte[] sign(byte[] msg, PrivateKey sk) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(sk);
        signature.update(msg);
        return signature.sign();
    }

    /**
     * Verifies an ECDSA signature using SHA-1.
     */
    public static boolean verify(byte[] msg, byte[] sig, PublicKey vk) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initVerify(vk);
        signature.update(msg);
        return signature.verify(sig);
    }
}