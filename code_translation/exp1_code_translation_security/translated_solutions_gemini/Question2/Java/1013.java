import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.HexFormat;

/**
 * A Java class to demonstrate ECDSA signing, equivalent to the Python script.
 */
public class EcdsaSigner {

    /**
     * Signs a message using the provided ECDSA private key.
     *
     * @param message    The string message to sign.
     * @param privateKey The ECDSA private key (equivalent to Python's SigningKey).
     * @return The digital signature as a byte array.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        // Get an instance of the Signature object for ECDSA.
        // "SHA256withECDSA" is a standard algorithm. The Python ecdsa library
        // defaults to SHA-1, but SHA-256 is a more secure and common choice.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // Initialize the Signature object for signing with the private key.
        ecdsaSign.initSign(privateKey);

        // Update the Signature object with the message to be signed.
        // The message is converted to bytes using UTF-8, similar to Python's message.encode().
        ecdsaSign.update(message.getBytes(StandardCharsets.UTF_8));

        // Generate the signature.
        byte[] signature = ecdsaSign.sign();
        return signature;
    }

    /**
     * Generates a new ECDSA key pair.
     * This is the Java equivalent of Python's `SigningKey.generate()`.
     * The Python library defaults to the NIST192p curve. Here, we use a 256-bit key size,
     * which corresponds to the more common and secure NIST P-256 curve.
     *
     * @return A KeyPair containing the public and private keys.
     * @throws NoSuchAlgorithmException if the EC algorithm is not available.
     */
    public static KeyPair generateEcKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(256); // 256-bit key size for the curve
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * The main method demonstrates the signing process.
     */
    public static void main(String[] args) {
        try {
            // Generate a new ECDSA key pair, equivalent to SigningKey.generate()
            KeyPair keyPair = generateEcKeyPair();

            // The message to sign
            String message = "something";

            // Call the sign function with the message and the private key
            byte[] signature = sign(message, keyPair.getPrivate());

            // Print the signature. Since the signature is a byte array, we convert it
            // to a hexadecimal string for readable output.
            // Note: The output will be different on each run because a new key is generated every time.
            System.out.println("Signature (Hex): " + HexFormat.of().formatHex(signature));

        } catch (Exception e) {
            System.err.println("An error occurred during signing:");
            e.printStackTrace();
        }
    }
}