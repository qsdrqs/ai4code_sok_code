import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class EcdsaSignDemo {

    /**
     * Signs the supplied message with the given EC private key.
     *
     * @param message  The textual message to be signed.
     * @param key      An elliptic-curve private key.
     * @return         The signature bytes.
     * @throws Exception If the signing operation fails.
     */
    public static byte[] sign(String message, PrivateKey key) throws Exception {
        // You may choose another algorithm if you want to match Python’s default more closely.
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(key);
        signer.update(message.getBytes(StandardCharsets.UTF_8));
        return signer.sign();
    }

    public static void main(String[] args) throws Exception {
        /* Generate a key pair on the NIST P-192 curve, which is what
           ecdsa.SigningKey.generate() uses by default in Python. */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp192r1"));
        KeyPair keyPair = kpg.generateKeyPair();

        // Sign the message
        byte[] signature = sign("something", keyPair.getPrivate());

        // Just print the result as base64 to keep the console readable
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}