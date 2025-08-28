import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSASignature {

    public static byte[] signMessage(String message, String curveName) throws Exception {
        // Generate a random private key
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(curveName);
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Sign the message
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message.getBytes());

        byte[] signature = ecdsaSign.sign();
        return signature;
    }

    public static void main(String[] args) {
        try {
            String message = "Hello, ECDSA!";
            String curveName = "secp256r1"; // Example curve name
            byte[] signature = signMessage(message, curveName);

            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}