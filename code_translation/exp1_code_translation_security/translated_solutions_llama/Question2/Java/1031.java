import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Main {

    /**
     * Signs a message using a private key.
     * 
     * @param msg      The message to be signed.
     * @param privKey  The private key in bytes.
     * @return         The signature of the message in bytes.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(byte[] msg, byte[] privKey) throws Exception {
        // Load the private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privKey);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);

        // Update the signature with the message
        signature.update(msg);

        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        byte[] msg = message.getBytes();

        // Generate a private key (for demonstration purposes only)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256); // Initialize with a key size of 256 bits
        KeyPair keyPair = keyGen.generateKeyPair();
        byte[] privKey = keyPair.getPrivate().getEncoded();

        // Sign the message
        byte[] signature = sign(msg, privKey);

        // Print the signature
        System.out.println("Message: " + message);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}