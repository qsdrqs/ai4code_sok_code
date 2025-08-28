import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.ECNamedCurveTable;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class ECDSASignature {

    // Register Bouncy Castle as a security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Example private key (32 bytes, 64 hex chars)
        String privateKeyHex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
        byte[] privateKeyBytes = hexStringToByteArray(privateKeyHex);

        // Message to sign
        String message = "Hello";

        // Sign the message
        String signature = signMessage(message, privateKeyBytes);

        // Output the signature
        System.out.println(signature);
    }

    /**
     * Signs a message using ECDSA with the SECP256k1 curve.
     *
     * @param message The message to sign.
     * @param privateKeyBytes The private key in raw byte format.
     * @return The signature as a hex string.
     * @throws Exception If an error occurs during signing.
     */
    public static String signMessage(String message, byte[] privateKeyBytes) throws Exception {
        // Step 1: Get domain parameters for SECP256k1
        ECDomainParameters ecParams = getDomainParameters("secp256k1");

        // Step 2: Convert private key bytes to BigInteger
        BigInteger d = new BigInteger(1, privateKeyBytes); // 1 for positive

        // Step 3: Create ECPrivateKeyParameters
        ECPrivateKeyParameters privateKey = new ECPrivateKeyParameters(d, ecParams);

        // Step 4: Initialize ECDSASigner with the private key
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);

        // Step 5: Convert message to bytes and update signer
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        signer.update(messageBytes, 0, messageBytes.length);

        // Step 6: Generate DER-encoded signature
        byte[] derSignature = signer.generateSignature();

        // Step 7: Convert signature to hex string
        return bytesToHex(derSignature);
    }

    /**
     * Retrieves the domain parameters for the specified elliptic curve.
     *
     * @param curveName The name of the curve (e.g., "secp256k1").
     * @return The domain parameters for the curve.
     */
    private static ECDomainParameters getDomainParameters(String curveName) {
        org.bouncycastle.math.ec.X9ECParameters params = ECNamedCurveTable.getByName(curveName);
        return new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
    }

    /**
     * Converts a hexadecimal string to a byte array.
     *
     * @param s The hex string.
     * @return The corresponding byte array.
     */
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The byte array.
     * @return The corresponding hex string.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}