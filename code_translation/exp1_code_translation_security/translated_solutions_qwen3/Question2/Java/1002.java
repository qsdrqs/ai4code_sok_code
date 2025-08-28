import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSATest {

    /**
     * Signs the given data using ECDSA with SHA-256.
     *
     * @param key  The private key to use for signing.
     * @param data The data to be signed.
     * @return     The signature as a byte array.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(PrivateKey key, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(key);
        signature.update(data);
        return signature.sign();
    }

    /**
     * Main method to test the signing functionality.
     * - Adds Bouncy Castle as a security provider.
     * - Generates a private key using the SECP384R1 curve.
     * - Signs a sample data string.
     * - Prints the signature in Base64 format.
     */
    public static void main(String[] args) throws Exception {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate a private key using the SECP384R1 curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"));
        KeyPair keyPair = kpg.generateKeyPair();

        // Data to be signed
        byte[] data = "this is some data I'd like to sign".getBytes(StandardCharsets.UTF_8);

        // Sign the data
        byte[] sigBytes = sign(keyPair.getPrivate(), data);

        // Print the signature in Base64 format
        System.out.println(Base64.getEncoder().encodeToString(sigBytes));
    }
}