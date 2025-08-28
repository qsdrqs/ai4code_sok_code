import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    // Static initializer to register Bouncy Castle as a security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using ECDSA with the specified curve.
     * A new key pair is generated each time this method is called.
     *
     * @param message The message to be signed (raw bytes).
     * @param key     The name of the elliptic curve (e.g., "secp256r1").
     * @return The DER-encoded signature as a byte array.
     * @throws Exception If an error occurs during key generation or signing.
     */
    public static byte[] signMessage(byte[] message, String key) throws Exception {
        // Generate a new EC key pair for the specified curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(new ECGenParameterSpec(key));
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // Create a Signature object for SHA-1 with ECDSA
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(message);

        // Sign the message and return the DER-encoded signature
        return signature.sign();
    }
}