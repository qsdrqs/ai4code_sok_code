import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.security.Security;
import java.security.Signature;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.ECPrivateKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class EcdsaSigner {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using ECDSA with the SECP256k1 curve.
     *
     * @param message     The message to sign.
     * @param privateKey  The private key as a UTF-8 string (32 bytes).
     * @return            The DER-encoded signature as a byte array.
     * @throws Exception  If an error occurs during signing.
     */
    public static byte[] signMessage(String message, String privateKey) throws Exception {
        // Convert message and private key to bytes
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] privKeyBytes = privateKey.getBytes(StandardCharsets.UTF_8);

        // Get SECP256k1 curve parameters
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Convert private key bytes to a BigInteger (d)
        BigInteger d = new BigInteger(1, privKeyBytes);

        // Create ECPrivateKeySpec using the curve and private key scalar
        ECPrivateKeySpec keySpec = new ECPrivateKeySpec(d, ecSpec);

        // Generate the private key using Bouncy Castle provider
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        PrivateKey privKey = keyFactory.generatePrivate(keySpec);

        // Initialize the signature engine with SHA-1 and ECDSA
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(privKey);
        signature.update(messageBytes);

        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        byte[] signature = signMessage("hello world", "12345678901234567890123456789012");

        // Print the signature in Base64 for readability
        System.out.println(java.util.Base64.getEncoder().encodeToString(signature));
    }
}