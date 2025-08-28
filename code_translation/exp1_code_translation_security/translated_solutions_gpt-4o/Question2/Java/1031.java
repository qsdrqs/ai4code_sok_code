import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class MessageSigner {

    /**
     * Sign a message
     * @param msg The message to sign
     * @param privateKey The private key used for signing
     * @return The signature as a byte array
     * @throws Exception If an error occurs during signing
     */
    public static byte[] sign(byte[] msg, PrivateKey privateKey) throws Exception {
        // Create a SHA-256 digest
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedMessage = digest.digest(msg);

        // Sign the hashed message using the private key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(hashedMessage);

        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, world!";
        String privateKeyBase64 = "YOUR_PRIVATE_KEY_IN_BASE64"; // Replace with your private key in Base64 format

        // Decode the private key from Base64
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Sign the message
        byte[] signature = sign(message.getBytes(), privateKey);

        // Print the signature in Base64 format
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}