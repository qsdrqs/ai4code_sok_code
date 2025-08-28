import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.math.BigInteger;
import java.security.Security;
import java.nio.charset.StandardCharsets;

public class ECDSASignerExample {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using the provided ECDSA private key.
     *
     * @param message       The message to sign (as a byte array).
     * @param privateKeyBytes The private key (32-byte array).
     * @return A 64-byte array containing the signature (r || s).
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKeyBytes) throws Exception {
        // Step 1: Hash the message using SHA-1
        SHA1Digest digest = new SHA1Digest();
        byte[] hash = new byte[digest.getDigestSize()];
        digest.update(message, 0, message.length);
        digest.doFinal(hash, 0);

        // Step 2: Create ECPrivateKeyParameters from the private key bytes
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(
                ecSpec.getCurve(), ecSpec.getG(), ecSpec.getN(), ecSpec.getH()
        );

        BigInteger d = new BigInteger(1, privateKeyBytes);
        ECPrivateKeyParameters keyParams = new ECPrivateKeyParameters(d, domainParams);

        // Step 3: Sign the hash using ECDSA
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyParams);
        BigInteger[] signature = signer.generateSignature(hash);

        // Step 4: Convert r and s to 32-byte arrays (big-endian)
        byte[] rBytes = to32ByteArray(signature[0]);
        byte[] sBytes = to32ByteArray(signature[1]);

        // Step 5: Concatenate r and s to form the 64-byte signature
        byte[] result = new byte[64];
        System.arraycopy(rBytes, 0, result, 0, 32);
        System.arraycopy(sBytes, 0, result, 32, 32);

        return result;
    }

    /**
     * Converts a BigInteger to a 32-byte array in big-endian format.
     * Pads with leading zeros if necessary.
     */
    private static byte[] to32ByteArray(BigInteger num) {
        byte[] bytes = num.toByteArray();
        if (bytes.length == 32) {
            return bytes;
        } else if (bytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(bytes, 0, padded, 32 - bytes.length, bytes.length);
            return padded;
        } else {
            throw new IllegalArgumentException("Value exceeds 32 bytes");
        }
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        byte[] message = "hello".getBytes(StandardCharsets.UTF_8);
        byte[] privateKey = "qwertyuiopasdfghjklzxcvbnmqwerty".getBytes(StandardCharsets.UTF_8);

        byte[] signature = signMessage(message, privateKey);

        // Print the signature as a Base64 string for readability
        System.out.println(java.util.Base64.getEncoder().encodeToString(signature));
    }
}