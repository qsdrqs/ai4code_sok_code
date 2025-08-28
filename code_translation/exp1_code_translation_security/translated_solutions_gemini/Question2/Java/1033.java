import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * A utility class for handling ECDSA signing operations.
 */
public class EcdsaSigner {

    /**
     * Signs a given message using a newly generated ECDSA key for a specified curve.
     *
     * Note: This function generates a new private/public key pair for each call,
     * which is equivalent to the provided Python code's logic. The private key is
     * discarded after signing.
     *
     * @param message The message to be signed, as a byte array.
     * @param curveName The standard name of the elliptic curve to use (e.g., "secp256r1").
     * @return The ECDSA signature as a byte array in DER format.
     * @throws Exception if any cryptographic error occurs (e.g., invalid curve name).
     */
    public static byte[] signMessage(byte[] message, String curveName) throws Exception {
        // 1. Generate a new key pair for the given curve
        // This corresponds to Python's: signing_key = SigningKey.generate(curve=key)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(curveName);
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // The public key (verifying_key) is derived but not used for signing,
        // matching the Python code's behavior where verifying_key is created but unused.
        // PublicKey publicKey = keyPair.getPublic();

        // 2. Sign the message
        // This corresponds to Python's: signature = signing_key.sign(message)
        // "SHA256withECDSA" is a standard algorithm choice for ECDSA.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        byte[] signature = ecdsaSign.sign();

        // 3. Return the signature
        return signature;
    }

    /**
     * Main method to demonstrate the usage of the signMessage function.
     */
    public static void main(String[] args) {
        try {
            // The message to sign
            String originalMessage = "This is a test message.";
            byte[] messageBytes = originalMessage.getBytes(StandardCharsets.UTF_8);

            // The curve to use (secp256r1 is equivalent to NIST P-256)
            String curve = "secp256r1";

            // Call the signMessage function
            // Note: Since a new key is generated inside signMessage, we cannot verify
            // the signature here without modifying the function to return the public key as well.
            // The function is a direct translation of the Python original.
            byte[] signature = signMessage(messageBytes, curve);

            System.out.println("Original Message: " + originalMessage);
            System.out.println("Signature generated successfully.");
            // Printing the signature in a readable format (Hex)
            System.out.println("Signature (Hex): " + bytesToHex(signature));

        } catch (Exception e) {
            System.err.println("Error during signing: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Helper function to convert a byte array to a hexadecimal string for display.
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