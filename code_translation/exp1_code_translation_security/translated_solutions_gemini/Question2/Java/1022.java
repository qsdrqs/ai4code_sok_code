import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * This class demonstrates ECDSA (Elliptic Curve Digital Signature Algorithm)
 * key generation, signing, and verification in Java, equivalent to the provided
 * Python script.
 *
 * It uses Java's built-in security libraries (JCA/JCE).
 */
public class EcdsaExample {

    public static void main(String[] args) {
        try {
            // The Python library uses the secp256k1 curve by default.
            // We specify the same curve in Java.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecSpec, new SecureRandom());

            // 1. Generate Keys (equivalent to PrivateKey() and privateKey.publicKey())
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // The message to be signed
            String message = "My test message";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            // 2. Generate Signature (equivalent to Ecdsa.sign(message, privateKey))
            // The signature algorithm "SHA256withECDSA" hashes the message with SHA-256
            // before signing it with ECDSA.
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(privateKey);
            ecdsaSign.update(messageBytes);
            byte[] signature = ecdsaSign.sign();

            // 3. Verify if signature is valid (equivalent to Ecdsa.verify(message, signature, publicKey))
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(messageBytes);
            boolean isVerified = ecdsaVerify.verify(signature);

            // Print the verification result (will be "true")
            System.out.println(isVerified);

        } catch (Exception e) {
            // Handle potential exceptions from cryptographic operations
            e.printStackTrace();
        }
    }
}