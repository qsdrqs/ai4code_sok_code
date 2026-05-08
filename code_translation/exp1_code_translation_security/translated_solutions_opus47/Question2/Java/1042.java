import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MessageSigner {

    /**
     * Signs a given message using ECDSA signing key
     * @param message message to be signed
     * @param privateKey private key to sign the message
     * @return signature of the message
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Load SECP256k1 curve parameters (equivalent to ecdsa.SECP256k1)
        X9ECParameters curveParams = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(
                curveParams.getCurve(),
                curveParams.getG(),
                curveParams.getN(),
                curveParams.getH()
        );

        // Build signing key from raw private key bytes
        // (equivalent to ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1))
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeyParameters signerKey = new ECPrivateKeyParameters(privateKeyInt, domainParams);

        // python-ecdsa's sign() hashes the message with SHA-1 by default
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] messageHash = digest.digest(message);

        // Sign the hashed message (equivalent to signer.sign(message))
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, signerKey);
        BigInteger[] signatureComponents = signer.generateSignature(messageHash);

        // python-ecdsa returns the signature as raw r||s (64 bytes for SECP256k1)
        byte[] r = toFixedLengthBytes(signatureComponents[0], 32);
        byte[] s = toFixedLengthBytes(signatureComponents[1], 32);

        byte[] signature = new byte[64];
        System.arraycopy(r, 0, signature, 0, 32);
        System.arraycopy(s, 0, signature, 32, 32);

        return signature;
    }

    /**
     * Converts a BigInteger to a fixed-length byte array (padded with zeros if needed,
     * or stripped of a leading sign byte if present).
     */
    private static byte[] toFixedLengthBytes(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        byte[] result = new byte[length];
        if (bytes.length <= length) {
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        } else {
            // Strip leading sign byte from two's complement encoding
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        }
        return result;
    }
}