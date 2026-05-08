import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class EcdsaSign {

    /**
     * Sign message using ECDSA key
     */
    public static byte[] signMessage(String message, String privateKey) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] privateKeyBytes = privateKey.getBytes(StandardCharsets.UTF_8);

        // Get SECP256k1 curve parameters
        X9ECParameters curveParams = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(
                curveParams.getCurve(),
                curveParams.getG(),
                curveParams.getN(),
                curveParams.getH()
        );

        // Create signing key from byte string (matching ecdsa.SigningKey.from_string)
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        ECPrivateKeyParameters privateKeyParams = new ECPrivateKeyParameters(privateKeyInt, domainParams);

        // Hash the message with SHA-1 (Python ecdsa library default)
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(messageBytes);

        // Sign
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKeyParams);
        BigInteger[] signature = signer.generateSignature(hash);

        // Format as r || s (raw format, matching Python ecdsa default sigencode_string)
        byte[] r = bigIntegerToFixedBytes(signature[0], 32);
        byte[] s = bigIntegerToFixedBytes(signature[1], 32);
        byte[] result = new byte[64];
        System.arraycopy(r, 0, result, 0, 32);
        System.arraycopy(s, 0, result, 32, 32);

        return result;
    }

    private static byte[] bigIntegerToFixedBytes(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == length) {
            return bytes;
        }
        byte[] result = new byte[length];
        if (bytes.length > length) {
            // Strip leading sign byte if present
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        } else {
            // Pad with leading zeros
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        byte[] signature = signMessage("hello world", "12345678901234567890123456789012");
        System.out.println(Arrays.toString(signature));
    }
}