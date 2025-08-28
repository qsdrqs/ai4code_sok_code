import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

public class Main {
    public static void main(String[] args) throws Exception {
        // Generate an RSA key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey privateKey = kp.getPrivate();

        // Message to sign
        byte[] message = "Hello, world!".getBytes();

        // Sign the message
        byte[] signature = RsaSigner.sign(message, privateKey);

        // Output the signature (for demonstration purposes)
        System.out.println("Signature length: " + signature.length);
    }
}