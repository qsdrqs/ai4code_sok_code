import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Generate an ECDSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(256); // Using a 256-bit key
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            System.out.println("Key Pair generated successfully.");
            System.out.println("Algorithm: " + privateKey.getAlgorithm());
            System.out.println("---");

            // 2. Define a message to sign
            String originalMessage = "This is a test message for ECDSA signing.";
            byte[] messageBytes = originalMessage.getBytes(StandardCharsets.UTF_8);
            System.out.println("Original Message: " + originalMessage);
            System.out.println("---");

            // 3. Sign the message using our function
            byte[] signature = EcdsaSigner.signMessage(messageBytes, privateKey);

            // The signature is binary data, so we print it in Base64 for readability
            String signatureBase64 = Base64.getEncoder().encodeToString(signature);
            System.out.println("Signature (Base64): " + signatureBase64);
            System.out.println("---");

            // (Optional but recommended) 4. Verify the signature
            boolean isVerified = verifySignature(messageBytes, signature, publicKey);
            System.out.println("Was the signature verified successfully? " + isVerified);

        } catch (GeneralSecurityException e) {
            System.err.println("A security error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A helper method to verify the ECDSA signature.
     */
    public static boolean verifySignature(byte[] message, byte[] signature, PublicKey publicKey) throws GeneralSecurityException {
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }

    // --- The translated function from the request ---
    public static class EcdsaSigner {
        public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws GeneralSecurityException {
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(signingKey);
            ecdsaSign.update(message);
            byte[] signature = ecdsaSign.sign();
            return signature;
        }
    }
}