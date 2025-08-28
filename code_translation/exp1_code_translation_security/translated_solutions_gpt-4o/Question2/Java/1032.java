import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class ECDSASignature {

    public static byte[] signMessage(String message, byte[] privateKeyBytes) throws Exception {
        // Hash the message using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes("UTF-8"));

        // Create a private key from the given byte array
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Initialize the signature with the private key
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);

        // Sign the message hash
        ecdsaSign.update(messageHash);
        byte[] signature = ecdsaSign.sign();

        // Return the signature
        return signature;
    }

    public static void main(String[] args) {
        try {
            String message = "Hello, world!";
            byte[] privateKeyBytes = DatatypeConverter.parseHexBinary("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725");
            byte[] signature = signMessage(message, privateKeyBytes);
            System.out.println(Base64.getEncoder().encodeToString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}