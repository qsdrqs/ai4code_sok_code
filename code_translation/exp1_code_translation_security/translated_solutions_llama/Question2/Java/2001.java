import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

// Function that signs input with the given ECDSA private key
public class ECDSASigner {

    public static byte[] sign(byte[] message, PrivateKey privateKey) throws Exception {
        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");

        // Initialize the signature object with the private key
        signature.initSign(privateKey);

        // Update the signature object with the message
        signature.update(message);

        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Generate a key pair (for demonstration purposes)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256); // Use a 256-bit key (secp256r1 curve)
        KeyPair keyPair = kpg.generateKeyPair();

        // Get the private key
        PrivateKey privateKey = keyPair.getPrivate();

        // Message to be signed
        String messageStr = "Hello, World!";
        byte[] message = messageStr.getBytes();

        // Sign the message
        byte[] signatureBytes = sign(message, privateKey);

        // Print the signature
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));
    }
}