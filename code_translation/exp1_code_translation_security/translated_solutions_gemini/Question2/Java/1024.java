import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * This class demonstrates how to generate an ECDSA signature for a message
 * using a private key in Java.
 */
public class EcdsaSignatureGenerator {

    /**
     * Signs a message using the ECDSA algorithm (specifically, SHA256withECDSA).
     *
     * @param message    The string message to sign.
     * @param privateKey The private key to use for signing.
     * @return A Base64 encoded string of the resulting signature.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     * @throws InvalidKeyException      If the provided key is invalid.
     * @throws SignatureException       If an error occurs during the signing process.
     */
    public static String generateEcdsaSignedMsg(String message, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // Get an instance of the Signature object for ECDSA
        // "SHA256withECDSA" is a standard algorithm for this purpose
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // Initialize the signature object with the private key for signing
        ecdsaSign.initSign(privateKey);

        // Add the message bytes to the signature object
        ecdsaSign.update(message.getBytes(StandardCharsets.UTF_8));

        // Calculate the signature
        byte[] signatureBytes = ecdsaSign.sign();

        // Encode the signature into a Base64 string for easy printing and transport
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    /**
     * The main method to demonstrate the functionality.
     * It generates a new key pair, signs a test message, and prints the signature.
     */
    public static void main(String[] args) {
        try {
            // 1. Generate Keys
            // The Python library uses the "secp256k1" curve by default
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic(); // Derived but not used for signing

            // 2. Define the message
            String message = "My test message";

            // 3. Generate and print the signature
            String signature = generateEcdsaSignedMsg(message, privateKey);
            
            System.out.println("Message: " + message);
            System.out.println("Generated Signature (Base64):");
            System.out.println(signature);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidAlgorithmParameterException e) {
            // Handle potential cryptographic exceptions
            System.err.println("Error during signature generation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}