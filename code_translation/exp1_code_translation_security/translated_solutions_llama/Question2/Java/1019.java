import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate a private key
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(384); // Use SECP384R1 curve
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey privateKey = kp.getPrivate();

        // Data to be signed
        String data = "hello world";
        byte[] dataBytes = data.getBytes();

        // Sign the data
        byte[] signature = signString(privateKey, dataBytes);

        // Print the signature
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Signs the given data using the provided private key.
     *
     * @param privateKey The private key to use for signing.
     * @param data       The data to be signed.
     * @return The signature of the data.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signString(PrivateKey privateKey, byte[] data) throws Exception {
        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");

        // Initialize the signature object with the private key
        signature.initSign(privateKey);

        // Update the signature object with the data
        signature.update(data);

        // Sign the data
        return signature.sign();
    }
}