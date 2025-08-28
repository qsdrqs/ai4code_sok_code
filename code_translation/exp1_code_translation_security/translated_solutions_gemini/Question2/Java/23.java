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
import java.util.Formatter;

/**
 * This class demonstrates how to perform an ECDSA signature in Java,
 * equivalent to the provided Python script using the 'ecdsa' library.
 */
public class EcdsaSigner {

    /**
     * Signs a message using the ECDSA algorithm with the provided private key.
     *
     * @param message The byte array of the message to be signed.
     * @param key The PrivateKey to use for signing.
     * @return The resulting signature as a byte array.
     * @throws NoSuchAlgorithmException If the cryptographic algorithm is not available.
     * @throws InvalidKeyException If the provided key is invalid for signing.
     * @throws SignatureException If an error occurs during the signing process.
     */
    public static byte[] sign(byte[] message, PrivateKey key)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // The Python ecdsa library often defaults to a hash based on the curve size.
        // "SHA256withECDSA" is a common and secure choice for the signature algorithm.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(key);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    /**
     * Helper method to convert a byte array to a hexadecimal string for display.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        try (Formatter formatter = new Formatter(sb)) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            // In Python: SigningKey.generate() # uses NIST192p
            // In Java, we generate a KeyPair using a KeyPairGenerator.
            // The curve "secp192r1" is the standard name for NIST P-192.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1");
            keyGen.initialize(ecSpec, new SecureRandom());
            
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate(); // This is the equivalent of Python's SigningKey

            // The message to be signed.
            // In Python: b"i am a message"
            String message = "i am a message";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            // Call the sign function with the message and the generated key.
            byte[] signature = sign(messageBytes, privateKey);

            // Print the signature. In Python, printing bytes gives a specific
            // representation (e.g., b'\xde\xad...'). Here, we print the raw
            // signature as a more readable hexadecimal string.
            System.out.println(bytesToHex(signature));

        } catch (Exception e) {
            // Catch all exceptions for simplicity in this example.
            e.printStackTrace();
        }
    }
}