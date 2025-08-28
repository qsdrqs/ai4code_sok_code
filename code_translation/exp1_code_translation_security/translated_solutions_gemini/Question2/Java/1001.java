import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * This class demonstrates how to sign a message using ECDSA with SHA-256 in Java.
 */
public class ECDSASigner {

    /**
     * Signs a message using the provided ECDSA private key.
     * This is the direct translation of the Python function.
     *
     * @param privateKey The EC private key to sign with.
     * @param message    The message to be signed, as a byte array.
     * @return The signature as a byte array.
     * @throws NoSuchAlgorithmException If the "SHA256withECDSA" algorithm is not available.
     * @throws InvalidKeyException      If the provided key is invalid.
     * @throws SignatureException       If an error occurs during the signing process.
     */
    public static byte[] signUsingECDSA(PrivateKey privateKey, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 1. Get a Signature instance for ECDSA with SHA-256
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the Signature object with the private key
        ecdsaSign.initSign(privateKey);

        // 3. Add the message data to be signed
        ecdsaSign.update(message);

        // 4. Generate the signature
        return ecdsaSign.sign();
    }

    // --- Main method for a complete, runnable example ---
    public static void main(String[] args) {
        try {
            // 1. Generate an EC Key Pair (equivalent to getting the 'key' object)
            // We'll use the secp256r1 curve, a common standard.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 2. Define the message to be signed
            String originalMessage = "This is the message to be signed";
            byte[] messageBytes = originalMessage.getBytes(StandardCharsets.UTF_8);

            // 3. Call the signing method
            byte[] signature = signUsingECDSA(privateKey, messageBytes);

            // Print the results
            System.out.println("Original Message: " + originalMessage);
            // Signatures are binary data, so we Base64-encode them for printing
            System.out.println("Generated Signature (Base64): " + Base64.getEncoder().encodeToString(signature));

            // 4. (Optional but recommended) Verify the signature to confirm it's correct
            boolean isVerified = verifyECDSASignature(publicKey, messageBytes, signature);
            System.out.println("Is the signature valid? " + isVerified);

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | SignatureException e) {
            System.err.println("An error occurred during the cryptographic process.");
            e.printStackTrace();
        }
    }

    /**
     * Helper method to verify an ECDSA signature.
     */
    public static boolean verifyECDSASignature(PublicKey publicKey, byte[] message, byte[] signature)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }
}