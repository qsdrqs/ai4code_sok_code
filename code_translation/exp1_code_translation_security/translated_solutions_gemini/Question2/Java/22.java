import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * This class provides the Java equivalent of the Python ecdsa signing function.
 */
public class EcdsaSigner {

    // Statically register the Bouncy Castle provider.
    // This is necessary to use its cryptographic algorithms.
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generates an ECDSA key pair and signs the given message.
     *
     * @param message The message to be signed, as a byte array.
     * @return The ECDSA signature as a byte array.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] myfunction(byte[] message) throws Exception {
        // Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
        // 1. Generate a new key pair using the secp256k1 curve.
        // We specify "ECDSA" as the algorithm and "BC" (Bouncy Castle) as the provider.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // Python: sig = sk.sign(message)
        // 2. Create a Signature object and initialize it for signing.
        // The Python ecdsa library typically uses SHA-256 as the default hash function.
        // The corresponding algorithm name in Java is "SHA256withECDSA".
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);

        // 3. Update the signature object with the message data and generate the signature.
        ecdsaSign.update(message);
        byte[] signature = ecdsaSign.sign();

        // The original Python function calculates the signature but doesn't return it.
        // A useful Java method would return the signature, so we do that here.
        return signature;
    }

    /**
     * A main method to demonstrate the usage of the myfunction method.
     */
    public static void main(String[] args) {
        try {
            // The message to sign.
            String message = "Here is a message to sign";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            // Call the function to get the signature.
            byte[] signature = myfunction(messageBytes);

            System.out.println("Original Message: " + message);
            System.out.println("Signature (Hex): " + bytesToHex(signature));
            System.out.println("Signature Length: " + signature.length + " bytes");

        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
    }

    /**
     * Helper function to convert a byte array to a hexadecimal string for display.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}