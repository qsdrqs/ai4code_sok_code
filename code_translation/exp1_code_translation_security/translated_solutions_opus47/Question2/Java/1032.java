import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;

/**
 * Java program that signs a given message using a given ECDSA signing key
 */
public class ECDSASign {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Sign a message using a given ECDSA private key
     */
    public static byte[] signMessage(String message, byte[] privateKey) throws Exception {
        // Hash the message
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        // Create a new ECDSA private key object using SECP256k1 curve
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(
                params.getCurve(), params.getG(), params.getN(), params.getH()
        );
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privateKeyInt, domainParams);

        // Sign the message hash
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privKeyParams);
        BigInteger[] signature = signer.generateSignature(messageHash);

        // Return the signature (r || s format, 64 bytes for SECP256k1)
        byte[] r = toFixedSizeByteArray(signature[0], 32);
        byte[] s = toFixedSizeByteArray(signature[1], 32);
        byte[] result = new byte[64];
        System.arraycopy(r, 0, result, 0, 32);
        System.arraycopy(s, 0, result, 32, 32);
        return result;
    }

    /**
     * Convert a BigInteger to a fixed-size big-endian byte array
     */
    private static byte[] toFixedSizeByteArray(BigInteger value, int size) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == size) {
            return bytes;
        } else if (bytes.length < size) {
            byte[] result = new byte[size];
            System.arraycopy(bytes, 0, result, size - bytes.length, bytes.length);
            return result;
        } else {
            // Remove leading sign byte if present
            byte[] result = new byte[size];
            System.arraycopy(bytes, bytes.length - size, result, 0, size);
            return result;
        }
    }

    /**
     * Convert a hexadecimal string to a byte array (equivalent of binascii.unhexlify)
     */
    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Convert a byte array to a hexadecimal string (for printing)
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Test function
     */
    public static void main(String[] args) throws Exception {
        String message = "Hello, world!";
        byte[] privateKey = hexStringToByteArray("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725");
        byte[] signature = signMessage(message, privateKey);
        System.out.println(bytesToHex(signature));
    }
}