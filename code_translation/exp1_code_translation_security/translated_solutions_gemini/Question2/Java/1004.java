import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.AlgorithmParameters;

/**
 * A Java class to demonstrate ECDSA message signing, equivalent to the Python script.
 * It uses the secp256k1 curve, the same one used by Bitcoin.
 */
public class EcdsaSigner {

    /**
     * Function sign_message
     *
     * Input: message and ECDSA key
     * Output: message signed using the provided key
     *
     * @param message The message to sign as a byte array.
     * @param privateKeyBytes The raw 32-byte private key.
     * @return The signature as a byte array in DER format.
     * @throws Exception if any security-related error occurs.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKeyBytes) throws Exception {
        // In Java, we construct the key from its raw byte value and the curve parameters.
        // 1. Get curve parameters for secp256k1.
        AlgorithmParameters params = AlgorithmParameters.getInstance("EC");
        params.init(new ECGenParameterSpec("secp256k1"));
        ECParameterSpec ecParameterSpec = params.getParameter(ECParameterSpec.class);

        // 2. Create the private key spec from the raw byte value.
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecParameterSpec);

        // 3. Get a KeyFactory instance and generate the PrivateKey.
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 4. Create a Signature object.
        // The python `ecdsa` library uses SHA1 by default for the hash function.
        // To match its output, we must use "SHA1withECDSA".
        Signature ecdsaSign = Signature.getInstance("SHA1withECDSA");

        // 5. Initialize the Signature object with the private key.
        ecdsaSign.initSign(privateKey);

        // 6. Add the message data to be signed.
        ecdsaSign.update(message);

        // 7. Generate the signature.
        byte[] signature = ecdsaSign.sign();
        return signature;
    }

    /**
     * Helper function to mimic Python's `print(bytes_object)` output format.
     * It prints printable ASCII characters and hex-escapes the rest.
     *
     * @param bytes The byte array to represent.
     * @return A string representation similar to Python's bytes literal (e.g., b'hello\x01').
     */
    private static String toPythonBytesString(byte[] bytes) {
        if (bytes == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("b'");
        for (byte b : bytes) {
            // Printable ASCII characters (except ' and \)
            if (b >= 32 && b <= 126 && b != '\'' && b != '\\') {
                sb.append((char) b);
            } else {
                // Hex escape for all other characters
                sb.append(String.format("\\x%02x", b & 0xFF));
            }
        }
        sb.append("'");
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            // Prepare the message and private key from strings, encoded in UTF-8.
            byte[] message = "hello".getBytes(StandardCharsets.UTF_8);
            // The private key for secp256k1 must be 32 bytes long.
            byte[] privateKey = "qwertyuiopasdfghjklzxcvbnmqwerty".getBytes(StandardCharsets.UTF_8);

            // Call the signMessage function.
            byte[] signature = signMessage(message, privateKey);

            // The Python `print()` on a bytes object produces a specific string format.
            // We use a helper function to replicate that format for a faithful translation.
            System.out.println(toPythonBytesString(signature));

        } catch (Exception e) {
            // Print any errors that occur during the signing process.
            e.printStackTrace();
        }
    }
}