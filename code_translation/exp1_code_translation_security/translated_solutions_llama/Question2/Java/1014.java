import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.util.Base64;

/**
 * Functions that signs a given message using a given ECDSA signing key
 */
public class ECDSASigner {

    /**
     * Signs a given message using a given ECDSA signing key
     * 
     * @param message     The message to be signed
     * @param privateKey  The private key used for signing
     * @return            The signature of the message in DER format
     * @throws Exception  If an error occurs during signing
     */
    public static byte[] signMessage(byte[] message, PrivateKey privateKey) throws Exception {
        // Hash the message
        byte[] messageHash = hashMessage(message);

        // Sign the message hash
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(messageHash);
        return signature.sign();
    }

    /**
     * Hashes a given message using SHA-256
     * 
     * @param message The message to be hashed
     * @return        The SHA-256 hash of the message
     */
    private static byte[] hashMessage(byte[] message) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
        return messageDigest.digest(message);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256); // Initialize with a key size of 256 bits
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        byte[] signature = signMessage(message.getBytes(), privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}