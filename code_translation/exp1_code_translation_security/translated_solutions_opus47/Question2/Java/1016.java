import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class in Java that signs a given message using a given ECDSA signing key.
 */
public class ECDSAUtil {

    /**
     * Sign a message using a given ECDSA signing key.
     *
     * @param message    the message to sign
     * @param privateKey the private key as a byte array (32 bytes for SECP256k1)
     * @return the signed message (64 bytes: r concatenated with s)
     */
    public static byte[] sign(byte[] message, byte[] privateKey) throws NoSuchAlgorithmException {
        // Create a new ECDSA signature object with SECP256k1 curve
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters curve = new ECDomainParameters(
                params.getCurve(), params.getG(), params.getN(), params.getH()
        );
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeyParameters privateKeyParams = new ECPrivateKeyParameters(privateKeyInt, curve);

        // Hash the message (SHA-1 is the default in Python's ecdsa library)
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] messageHash = digest.digest(message);

        // Sign the message using the ECDSA signature object
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKeyParams);
        BigInteger[] components = signer.generateSignature(messageHash);

        // Return the signed message (r || s format, matching Python's ecdsa default output)
        byte[] signedMessage = new byte[64];
        byte[] r = toFixedLengthBytes(components[0], 32);
        byte[] s = toFixedLengthBytes(components[1], 32);
        System.arraycopy(r, 0, signedMessage, 0, 32);
        System.arraycopy(s, 0, signedMessage, 32, 32);

        return signedMessage;
    }

    /**
     * Convert a BigInteger to a fixed-length unsigned byte array.
     */
    private static byte[] toFixedLengthBytes(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == length) {
            return bytes;
        }
        byte[] result = new byte[length];
        if (bytes.length > length) {
            // Strip leading sign byte
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        } else {
            // Left-pad with zeros
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        }
        return result;
    }
}