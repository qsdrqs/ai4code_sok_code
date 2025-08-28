import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;

public class EcdsaExample {

    /**
     * Signs the constant string "message" with the provided EC private key.
     *
     * @param msg  (Unused) – included only to mirror the Python signature.
     * @param key  EC private key used for signing.
     * @return     The ECDSA signature bytes.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] sign(byte[] msg, PrivateKey key) throws Exception {
        // Create a Signature object for ECDSA (no hashing, just raw signing).
        // If you need hashing use "SHA256withECDSA" or similar.
        Signature signer = Signature.getInstance("NONEwithECDSA");

        // Initialize with the private key
        signer.initSign(key);

        // Sign the literal string "message", matching the Python code
        signer.update("message".getBytes(StandardCharsets.UTF_8));

        // Produce and return the signature
        return signer.sign();
    }
}