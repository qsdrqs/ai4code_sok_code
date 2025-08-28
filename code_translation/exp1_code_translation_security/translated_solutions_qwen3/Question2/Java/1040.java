import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.crypto.params.ParametersWithRandom;

import java.math.BigInteger;
import java.security.Security;

public class ECDSASignerUtil {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using ECDSA with the SECP256k1 curve.
     * The signature is returned as a 64-byte array: r (32 bytes) || s (32 bytes).
     *
     * @param message      The message to be signed (raw bytes).
     * @param privateKey   The private key as a 32-byte array.
     * @return             The signature as a 64-byte array.
     * @throws Exception   If an error occurs during signing.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Get curve parameters for SECP256k1
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECDomainParameters ecDomainParams = new ECDomainParameters(
                ecSpec.getCurve(),
                ecSpec.getG(),
                ecSpec.getN(),
                ecSpec.getH()
        );

        // Convert private key bytes to a BigInteger
        BigInteger d = new BigInteger(1, privateKey); // (signum, magnitude)

        // Create private key parameters
        ECPrivateKeyParameters keyParams = new ECPrivateKeyParameters(d, ecDomainParams);

        // Initialize the ECDSA signer
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ParametersWithRandom(keyParams));

        // Hash the message using SHA-1 (same as Python ecdsa's default)
        SHA1Digest digest = new SHA1Digest();
        digest.update(message, 0, message.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        // Generate the signature (r, s)
        BigInteger[] signature = signer.generateSignature(hash);

        // Convert r and s to 32-byte arrays
        byte[] rBytes = to32ByteArray(signature[0]);
        byte[] sBytes = to32ByteArray(signature[1]);

        // Concatenate r and s into a 64-byte array
        byte[] result = new byte[64];
        System.arraycopy(rBytes, 0, result, 0, 32);
        System.arraycopy(sBytes, 0, result, 32, 32);

        return result;
    }

    /**
     * Converts a BigInteger to a 32-byte array in big-endian format.
     * Pads with leading zeros if necessary.
     *
     * @param value The BigInteger to convert.
     * @return      A 32-byte array.
     */
    private static byte[] to32ByteArray(BigInteger value) {
        byte[] bytes = value.toByteArray();

        if (bytes.length == 32) {
            return bytes;
        }

        if (bytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(bytes, 0, padded, 32 - bytes.length, bytes.length);
            return padded;
        }

        // Handle case where the first byte is a zero due to two's complement
        if (bytes.length == 33 && bytes[0] == 0) {
            byte[] trimmed = new byte[32];
            System.arraycopy(bytes, 1, trimmed, 0, 32);
            return trimmed;
        }

        throw new IllegalArgumentException("Value exceeds 32 bytes: " + value.toString(16));
    }
}