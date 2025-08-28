import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        // Example: Load a private key from a Base64-encoded string
        String base64PrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7...";
        byte[] decodedKey = Base64.getDecoder().decode(base64PrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Example message to sign
        byte[] message = "Hello, world!".getBytes();

        // Sign the message
        byte[] signature = EcdsaSigner.signMessage(message, privateKey);

        // Output the signature in Base64 for readability
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}