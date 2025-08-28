import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * This class provides functionality for creating and verifying ECDSA signatures,
 * equivalent to the provided Python script.
 */
public class EcdsaSignatureExample {

    // The algorithm for ECDSA with SHA-256. This is a standard and secure choice.
    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";
    // The algorithm for Elliptic Curve key generation.
    private static final String KEY_GENERATION_ALGORITHM = "EC";
    // The specific curve to use. secp256r1 (NIST P-256) is standard and widely supported in Java.
    private static final String CURVE_SPEC = "secp256r1";

    /**
     * Signs a message using the given private key.
     *
     * @param privateKey The PrivateKey object (equivalent to SigningKey in Python).
     * @param message    The string message to sign.
     * @return The raw byte array of the signature.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] sign(PrivateKey privateKey, String message) throws Exception {
        // 1. Get an instance of the Signature object.
        Signature ecdsaSign = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 2. Initialize the Signature object for signing with the private key.
        ecdsaSign.initSign(privateKey);

        // 3. Add the message data to be signed. We convert the string to bytes using UTF-8.
        ecdsaSign.update(message.getBytes(StandardCharsets.UTF_8));

        // 4. Generate the signature.
        return ecdsaSign.sign();
    }

    /**
     * Verifies a signature against a message using the given public key.
     *
     * @param publicKey The PublicKey object (equivalent to VerifyingKey in Python).
     * @param signature The raw byte array of the signature to verify.
     * @param message   The original message data.
     * @return true if the signature is valid, false otherwise.
     * @throws Exception if any cryptographic error occurs.
     */
    public static boolean verify(PublicKey publicKey, byte[] signature, String message) throws Exception {
        // 1. Get an instance of the Signature object.
        Signature ecdsaVerify = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 2. Initialize the Signature object for verification with the public key.
        ecdsaVerify.initVerify(publicKey);

        // 3. Add the message data that the signature should correspond to.
        ecdsaVerify.update(message.getBytes(StandardCharsets.UTF_8));

        // 4. Verify the signature.
        return ecdsaVerify.verify(signature);
    }

    /**
     * A test method demonstrating the signing and verification process.
     * This is equivalent to the `test()` function in the Python code.
     */
    public static void test() throws Exception {
        // 1. Generate an ECDSA key pair.
        // This is equivalent to `sk = SigningKey.generate()`.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KEY_GENERATION_ALGORITHM);
        keyGen.initialize(new ECGenParameterSpec(CURVE_SPEC), new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        
        PrivateKey privateKey = keyPair.getPrivate(); // This is 'sk'
        PublicKey publicKey = keyPair.getPublic();   // This is 'vk'

        // 2. Sign the message "hello world".
        String originalMessage = "hello world";
        byte[] signature = sign(privateKey, originalMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Signature generated (first 16 bytes): " + bytesToHex(signature).substring(0, 32) + "...");

        // 3. Verify the signature against the *correct* message.
        boolean isCorrect = verify(publicKey, signature, originalMessage);
        System.out.println("\nVerifying signature with CORRECT message ('hello world')...");
        System.out.println("Verification successful: " + isCorrect); // Should be true

        // 4. Attempt to verify the signature against an *incorrect* message.
        // This is the equivalent of the final line in the Python test.
        String tamperedMessage = "hello worfld"; // Note the typo
        boolean isTamperedCorrect = verify(publicKey, signature, tamperedMessage);
        System.out.println("\nVerifying signature with TAMPERED message ('hello worfld')...");
        System.out.println("Verification successful: " + isTamperedCorrect); // Should be false
    }

    /**
     * Main method to run the test.
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.err.println("An error occurred during the test:");
            e.printStackTrace();
        }
    }
    
    /**
     * Helper utility to convert a byte array to a hexadecimal string for printing.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}