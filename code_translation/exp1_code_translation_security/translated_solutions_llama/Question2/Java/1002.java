import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.util.Base64;

public class Main {

    // Define the elliptic curve and hash algorithm
    private static final String CURVE = "secp384r1";
    private static final String HASH_ALGORITHM = "SHA-256";

    public static byte[] sign(PrivateKey privateKey, byte[] data) throws Exception {
        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Generate a private key
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE);
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // Data to be signed
        byte[] data = "this is some data I'd like to sign".getBytes();

        // Sign the data
        byte[] signature = sign(privateKey, data);

        // Print the signature
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}