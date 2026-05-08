import java.security.PrivateKey;
import java.security.Signature;

public class MessageSigner {
    // Function in Java that signs a given message using a given ECDSA signing key
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws Exception {
        // Create a Signature instance for ECDSA with SHA-256
        Signature signature = Signature.getInstance("SHA256withECDSA");
        // Initialize the signature object with the signing key
        signature.initSign(signingKey);
        // Update the signature object with the message bytes
        signature.update(message);
        // Sign the message and return the signature
        return signature.sign();
    }
}