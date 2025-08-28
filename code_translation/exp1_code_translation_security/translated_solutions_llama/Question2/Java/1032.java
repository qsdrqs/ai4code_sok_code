import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Main {

    /**
     * Sign a message using a given ECDSA private key
     *
     * @param message      The message to be signed
     * @param privateKey   The ECDSA private key
     * @return             The signature of the message
     * @throws Exception   If an error occurs during signing
     */
    public static byte[] signMessage(String message, byte[] privateKey) throws Exception {
        // Create a new SHA-256 message digest
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = messageDigest.digest(message.getBytes("UTF-8"));

        // Create a new ECDSA private key object
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey privateKeyObject = keyFactory.generatePrivate(privateKeySpec);

        // Sign the message hash
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKeyObject);
        signature.update(messageHash);
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Test function
        String message = "Hello, world!";
        String privateKeyHex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
        byte[] privateKey = java.util.HexFormat.decodeHex(privateKeyHex);

        byte[] signature = signMessage(message, privateKey);
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}