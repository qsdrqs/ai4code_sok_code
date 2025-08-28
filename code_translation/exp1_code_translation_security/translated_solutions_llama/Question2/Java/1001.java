import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ECDSASigner {

    /**
     * Signs a message using ECDSA.
     * 
     * @param key     The private key to use for signing.
     * @param message The message to be signed.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signUsingECDSA(byte[] key, byte[] message) throws Exception {
        // Load the private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);

        // Return the signature
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String privateKeyString = "your_private_key_here"; // Replace with your private key
        String messageString = "Hello, World!"; // Replace with your message

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        byte[] messageBytes = messageString.getBytes();

        byte[] signature = signUsingECDSA(privateKeyBytes, messageBytes);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}