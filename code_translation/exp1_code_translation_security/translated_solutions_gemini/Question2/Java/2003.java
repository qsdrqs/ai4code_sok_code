import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

/**
 * This class demonstrates how to generate an ECDSA key pair and sign a message,
 * equivalent to the provided Python script.
 */
public class EcdsaSigner {

    /**
     * Signs a string input using the provided ECDSA private key.
     *
     * @param input The string message to sign.
     * @param privateKey The ECDSA PrivateKey object.
     * @return A byte array containing the digital signature.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] sign(String input, PrivateKey privateKey) throws Exception {
        // The Python library defaults to SHA-256. "SHA256withECDSA" is the standard name in Java.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        
        // Initialize the signature object for signing with the private key.
        ecdsaSign.initSign(privateKey);
        
        // The Python code encodes the input string to UTF-8 bytes before signing.
        // We do the same here.
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        
        // Pass the data to be signed to the signature object.
        ecdsaSign.update(inputBytes);
        
        // Generate the signature.
        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            // 1. Generate an ECDSA key pair.
            // This is equivalent to Python's `SigningKey.generate()`.
            // We specify "EC" for Elliptic Curve and a key size of 256 bits, a common standard.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            
            // In Java, the signing key is the PrivateKey from the KeyPair.
            PrivateKey privateKey = keyPair.getPrivate();

            // 2. Sign the message "Hello world".
            String message = "Hello world";
            byte[] signature = sign(message, privateKey);

            // 3. Print the result.
            // Printing a raw byte array isn't very readable, so we encode it in Base64.
            // This is a common way to represent binary data as a string.
            System.out.println("Original Message: " + message);
            System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signature));

        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
    }
}