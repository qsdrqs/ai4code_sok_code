import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * This class provides functionality for ECDSA signing, equivalent to the Python script.
 * All dependencies are part of the standard Java Runtime Environment.
 */
public class EcdsaSigner {

    /**
     * Signs data using the provided ECDSA private key.
     * This is equivalent to `key.sign(data, ec.ECDSA(hashes.SHA256()))` in Python.
     *
     * @param key  The EC private key.
     * @param data The data to be signed.
     * @return The signature as a byte array.
     * @throws NoSuchAlgorithmException If the signature algorithm is not available.
     * @throws InvalidKeyException      If the provided key is invalid.
     * @throws SignatureException       If a signature error occurs.
     */
    public static byte[] sign(PrivateKey key, byte[] data)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // The algorithm "SHA256withECDSA" corresponds to Python's ec.ECDSA(hashes.SHA256())
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(key);
        ecdsaSign.update(data);
        return ecdsaSign.sign();
    }

    /**
     * A test function to demonstrate the signing process.
     * It generates a new key pair, signs some data, and prints the signature.
     */
    public static void test() throws Exception { // Using "throws Exception" for conciseness
        // Generate an ECDSA key pair using the secp384r1 curve.
        // This is equivalent to Python's ec.generate_private_key(ec.SECP384R1(), backend)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // The data to be signed, equivalent to b"this is some data I'd like to sign"
        String message = "this is some data I'd like to sign";
        byte[] data = message.getBytes(StandardCharsets.UTF_8);

        // Sign the data
        byte[] signature = sign(privateKey, data);

        // Print the signature. The raw bytes are not human-readable,
        // so we print the Base64 encoded version for display purposes.
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Main method to run the test.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.err.println("An error occurred during the signing test:");
            e.printStackTrace();
        }
    }
}