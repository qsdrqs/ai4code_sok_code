import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class ECDSASignature {

    // Function that signs input with the given ECDSA private key
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            // Example usage
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(new ECGenParameterSpec("secp256r1"));
            KeyPair keyPair = keyGen.generateKeyPair();

            String message = "Hello, World!";
            byte[] signature = sign(message.getBytes(), keyPair.getPrivate());

            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}