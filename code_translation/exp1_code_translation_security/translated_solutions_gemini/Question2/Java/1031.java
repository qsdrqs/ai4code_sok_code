import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class SigningExample {

    /**
     * Signs a message using a private key with the SHA256withRSA algorithm.
     *
     * @param msg The message to be signed, as a byte array.
     * @param privKey The private key (java.security.PrivateKey).
     * @return The resulting signature as a byte array.
     * @throws GeneralSecurityException if any cryptographic error occurs.
     */
    public static byte[] sign(byte[] msg, PrivateKey privKey) throws GeneralSecurityException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privKey);
        signer.update(msg);
        return signer.sign();
    }

    /**
     * Verifies a signature against a message and a public key.
     *
     * @param msg The original message as a byte array.
     * @param signatureBytes The signature to verify.
     * @param pubKey The public key corresponding to the private key used for signing.
     * @return true if the signature is valid, false otherwise.
     * @throws GeneralSecurityException if any cryptographic error occurs.
     */
    public static boolean verify(byte[] msg, byte[] signatureBytes, PublicKey pubKey) throws GeneralSecurityException {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(pubKey);
        verifier.update(msg);
        return verifier.verify(signatureBytes);
    }

    public static void main(String[] args) {
        try {
            // 1. Generate an RSA key pair (for demonstration purposes)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // Use a 2048-bit key size
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 2. Define the message to be signed
            String message = "This is the message to be signed.";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            System.out.println("Original Message: " + message);
            System.out.println("Algorithm: SHA256withRSA");
            System.out.println("------------------------------------");

            // 3. Sign the message using our sign method
            byte[] signature = sign(messageBytes, privateKey);

            // Print the signature (usually encoded in Base64 for transport/display)
            String base64Signature = Base64.getEncoder().encodeToString(signature);
            System.out.println("Generated Signature (Base64): " + base64Signature);
            System.out.println("------------------------------------");

            // 4. Verify the signature using the public key
            boolean isVerified = verify(messageBytes, signature, publicKey);

            System.out.println("Verification Result: " + (isVerified ? "SUCCESS" : "FAILURE"));

        } catch (GeneralSecurityException e) {
            System.err.println("A cryptographic error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}