import java.math.BigInteger;
import java.security.Security;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * A utility class for signing messages using ECDSA with the secp256k1 curve.
 * This class provides a Java equivalent to the Python ecdsa library's signing functionality.
 */
public class EcdsaSigner {

    private static final String CURVE_NAME = "secp256k1";
    private static final ECDomainParameters EC_DOMAIN_PARAMS;
    private static final int KEY_SIZE_BYTES = 32;

    // Static initializer to add Bouncy Castle as a security provider
    // and cache the curve parameters for efficiency.
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        X9ECParameters curveParams = CustomNamedCurves.getByName(CURVE_NAME);
        EC_DOMAIN_PARAMS = new ECDomainParameters(
                curveParams.getCurve(),
                curveParams.getG(),
                curveParams.getN(),
                curveParams.getH()
        );
    }

    /**
     * Signs a message using a private key with the secp256k1 curve.
     *
     * This method replicates the behavior of python-ecdsa's `SigningKey.sign`.
     * The `message` parameter should be a 32-byte hash (e.g., SHA-256) of the
     * actual content you wish to sign.
     *
     * The signature is returned in the raw (r || s) format, as a 64-byte array.
     *
     * @param message The 32-byte message hash to sign.
     * @param privateKey The 32-byte private key.
     * @return A 64-byte signature consisting of the 'r' and 's' values concatenated.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) {
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeyParameters privateKeyParams = new ECPrivateKeyParameters(privateKeyInt, EC_DOMAIN_PARAMS);

        ECDSASigner signer = new EDSASigner();
        signer.init(true, privateKeyParams); // 'true' initializes the signer for signing

        BigInteger[] signatureComponents = signer.generateSignature(message);
        BigInteger r = signatureComponents[0];
        BigInteger s = signatureComponents[1];

        // Note: For strict compatibility with systems like Bitcoin, 's' should be
        // canonicalized to its lower-half value. The original Python code does not
        // do this by default, so it is omitted here for a direct translation.

        byte[] rBytes = toFixedSizeByteArray(r, KEY_SIZE_BYTES);
        byte[] sBytes = toFixedSizeByteArray(s, KEY_SIZE_BYTES);

        byte[] signature = new byte[KEY_SIZE_BYTES * 2];
        System.arraycopy(rBytes, 0, signature, 0, KEY_SIZE_BYTES);
        System.arraycopy(sBytes, 0, signature, KEY_SIZE_BYTES, KEY_SIZE_BYTES);

        return signature;
    }

    /**
     * Converts a BigInteger to a byte array of a specific size.
     * This method handles removing the leading sign byte from the BigInteger's
     * internal byte array and left-pads with zeros if the array is shorter
     * than the desired size.
     *
     * @param value The BigInteger to convert.
     * @param size The desired length of the output byte array.
     * @return A byte array of length {@code size}.
     */
    private static byte[] toFixedSizeByteArray(BigInteger value, int size) {
        byte[] result = new byte[size];
        byte[] bigIntBytes = value.toByteArray();

        int bigIntStart = (bigIntBytes.length == size + 1 && bigIntBytes[0] == 0) ? 1 : 0;
        int bigIntLength = bigIntBytes.length - bigIntStart;

        if (bigIntLength > size) {
            throw new IllegalArgumentException("BigInteger is too large to fit in " + size + " bytes.");
        }

        int resultStart = size - bigIntLength;
        System.arraycopy(bigIntBytes, bigIntStart, result, resultStart, bigIntLength);
        return result;
    }
}