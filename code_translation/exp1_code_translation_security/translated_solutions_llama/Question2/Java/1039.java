import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSASigner {

    /**
     * Signs a message with the provided private key.
     *
     * @param message The message to be signed.
     * @param privateKey The private key used for signing.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signMessage(String message, byte[] privateKey) throws Exception {
        // Load the private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKeyInstance = keyFactory.generatePrivate(privateKeySpec);

        // Create a signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKeyInstance);

        // Update the signature with the message
        signature.update(message.getBytes());

        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        // Replace with your actual private key bytes
        byte[] privateKey = // Load or generate your private key here;

        byte[] signature = signMessage(message, privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}