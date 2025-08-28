import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ECDSAExample {

    /**
     * Signs a message using the provided private key and verifies the signature
     * using the corresponding public key.
     *
     * @param msg   The message to be signed (as a byte array).
     * @param key   The private key used for signing.
     * @param vk    The public key used for verification.
     * @return      The signature as a byte array.
     * @throws Exception If any cryptographic operation fails.
     */
    public static byte[] sign(byte[] msg, PrivateKey key, PublicKey vk) throws Exception {
        // Use SHA1withECDSA to match the default behavior of the Python ecdsa library
        Signature signature = Signature.getInstance("SHA1withECDSA");
        
        // Initialize for signing
        signature.initSign(key);
        signature.update(msg);
        byte[] sigBytes = signature.sign();

        // Initialize for verification
        signature.initVerify(vk);
        signature.update(msg);
        if (!signature.verify(sigBytes)) {
            throw new RuntimeException("Signature verification failed");
        }

        return sigBytes;
    }

    /**
     * Generates an ECDSA key pair, signs a test message, and prints the signature.
     * @throws Exception If any cryptographic operation fails.
     */
    public static void test() throws Exception {
        // Generate an ECDSA key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Message to be signed
        byte[] msg = "message".getBytes(StandardCharsets.UTF_8);

        // Sign the message
        byte[] signature = sign(msg, sk, vk);

        // Print the signature (as a byte array)
        System.out.println("Signature: " + Arrays.toString(signature));
    }

    /**
     * Main method to run the test.
     * @param args Command-line arguments (not used).
     * @throws Exception If any cryptographic operation fails.
     */
    public static void main(String[] args) throws Exception {
        test();
    }
}