import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSASigner {

    /**
     * Signs a given message using a provided ECDSA signing key.
     * 
     * @param message     The message to be signed.
     * @param signingKey  The ECDSA signing key.
     * @return            The signature of the message.
     * @throws Exception  If an error occurs during signing.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws Exception {
        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        
        // Initialize the signature object with the signing key
        signature.initSign(signingKey);
        
        // Update the signature object with the message
        signature.update(message);
        
        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage:
        // Generate or load your ECDSA signing key
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256); // Initialize with a key size of 256 bits
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey signingKey = keyPair.getPrivate();

        // Message to be signed
        String messageStr = "Hello, World!";
        byte[] message = messageStr.getBytes();

        // Sign the message
        byte[] signature = signMessage(message, signingKey);

        // Print the signature
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}