import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

/**
 * This class demonstrates how to sign a message using ECDSA with a SECP256k1 private key,
 * mimicking the behavior of the Python 'ecdsa' library.
 *
 * Dependencies:
 * This code requires the Bouncy Castle library.
 *
 * Maven:
 * <dependency>
 *     <groupId>org.bouncycastle</groupId>
 *     <artifactId>bcprov-jdk18on</artifactId>
 *     <version>1.77</version>
 * </dependency>
 *
 * Gradle:
 * implementation 'org.bouncycastle:bcprov-jdk18on:1.77'
 */
public class EcdsaSigner {

    /**
     * Signs a message using the provided private key string with the SECP256k1 curve.
     *
     * @param message The message string to sign.
     * @param privateKeyString The private key, represented as a 32-byte string.
     * @return The raw signature as a byte array (r || s).
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] signMessage(String message, String privateKeyString) throws Exception {
        // 1. Convert the message string to bytes using UTF-8.
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // 2. The Python 'ecdsa.SigningKey.from_string' method uses the raw bytes of the key.
        // We replicate this by getting the UTF-8 bytes of the private key string.
        byte[] privateKeyBytes = privateKeyString.getBytes(StandardCharsets.UTF_8);
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);

        // 3. Set up the ECDSA curve parameters (SECP256k1).
        ECNamedCurveParameterSpec curveParams = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, curveParams);

        // 4. Get the KeyFactory and generate the PrivateKey object.
        // We specify "BC" to use the Bouncy Castle provider.
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 5. The Python 'ecdsa' library defaults to SHA-1 for hashing and produces a
        // raw (r || s) signature, not a DER-encoded one. To replicate this in Java:
        // a) We manually hash the message with SHA-1.
        MessageDigest digest = MessageDigest.getInstance("SHA-1", "BC");
        byte[] messageHash = digest.digest(messageBytes);

        // b) We use the "NONEwithECDSA" algorithm, which signs a pre-computed hash
        // and produces the raw (r || s) signature format.
        Signature signature = Signature.getInstance("NONEwithECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(messageHash);

        return signature.sign();
    }

    public static void main(String[] args) {
        // Add Bouncy Castle as a security provider. This is necessary to use its
        // implementations of algorithms (like NONEwithECDSA) and curves (like secp256k1).
        Security.addProvider(new BouncyCastleProvider());

        String message = "hello world";
        // A 32-byte private key, same as in the Python example.
        String privateKey = "12345678901234567890123456789012";

        try {
            byte[] signature = signMessage(message, privateKey);
            // The Python code prints the raw bytes object representation.
            // For clarity and a standard representation, we'll print the signature as a hexadecimal string.
            System.out.println(bytesToHex(signature));
        } catch (Exception e) {
            System.err.println("Error signing message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to convert a byte array to a hexadecimal string.
     * @param bytes The byte array to convert.
     * @return The hexadecimal string representation.
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