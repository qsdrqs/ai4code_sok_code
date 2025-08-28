import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

public class ECDSAUtils {

    static {
        // Register Bouncy Castle provider
        org.bouncycastle.jce.provider.BouncyCastleProvider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        java.security.Security.addProvider(provider);
    }

    /**
     * Signs a message using ECDSA with the NIST P-256 curve and SHA-256.
     *
     * @param message The message to sign.
     * @param signingKeyRaw The raw 32-byte private key.
     * @return The 64-byte signature (r || s).
     */
    public static byte[] signNist256(byte[] message, byte[] signingKeyRaw) {
        try {
            // Get curve parameters for secp256r1
            X9ECParameters ecParams = SECNamedCurves.getByName("secp256r1");
            ECDomainParameters domainParams = new ECDomainParameters(ecParams);

            // Create private key
            BigInteger d = new BigInteger(1, signingKeyRaw);
            ECPrivateKeyParameters key = new ECPrivateKeyParameters(d, domainParams);

            // Compute SHA-256 hash
            byte[] hash = new byte[32];
            SHA256Digest digest = new SHA256Digest();
            digest.update(message, 0, message.length);
            digest.doFinal(hash, 0);

            // Sign hash
            ECDSASigner signer = new ECDSASigner();
            signer.init(true, key);
            BigInteger[] sig = signer.generateSignature(hash);

            // Encode signature as 64-byte array (r and s as 32 bytes each)
            byte[] signature = new byte[64];
            encodeBigIntTo32Bytes(sig[0], signature, 0);
            encodeBigIntTo32Bytes(sig[1], signature, 32);
            return signature;
        } catch (Exception e) {
            throw new RuntimeException("Error signing", e);
        }
    }

    /**
     * Verifies a message using ECDSA with the NIST P-256 curve and SHA-256.
     *
     * @param message The message to verify.
     * @param signature The 64-byte signature (r || s).
     * @param verifyingKeyRaw The raw public key bytes (compressed or uncompressed).
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean verifyNist256(byte[] message, byte[] signature, byte[] verifyingKeyRaw) {
        try {
            if (signature.length != 64) {
                return false;
            }

            // Get curve parameters for secp256r1
            X9ECParameters ecParams = SECNamedCurves.getByName("secp256r1");
            ECDomainParameters domainParams = new ECDomainParameters(ecParams);

            // Parse public key point
            ECPoint Q = ecParams.getCurve().decodePoint(verifyingKeyRaw);
            ECPublicKeyParameters key = new ECPublicKeyParameters(Q, domainParams);

            // Compute SHA-256 hash
            byte[] hash = new byte[32];
            SHA256Digest digest = new SHA256Digest();
            digest.update(message, 0, message.length);
            digest.doFinal(hash, 0);

            // Parse signature
            BigInteger r = decodeBigIntFrom32Bytes(signature, 0);
            BigInteger s = decodeBigIntFrom32Bytes(signature, 32);

            // Verify
            ECDSASigner signer = new ECDSASigner();
            signer.init(false, key);
            return signer.verifySignature(hash, r, s);
        } catch (Exception e) {
            return false;
        }
    }

    // Helper: Encodes a BigInteger as 32-byte big-endian
    private static void encodeBigIntTo32Bytes(BigInteger bi, byte[] buf, int offset) {
        byte[] bytes = bi.toByteArray();
        if (bytes.length > 32) {
            // Skip leading zero byte if present
            System.arraycopy(bytes, 1, buf, offset, 32);
        } else {
            // Fill leading bytes with zero if necessary
            int len = Math.min(bytes.length, 32);
            System.arraycopy(bytes, 0, buf, offset + 32 - len, len);
        }
    }

    // Helper: Decodes 32-byte big-endian into a BigInteger
    private static BigInteger decodeBigIntFrom32Bytes(byte[] buf, int offset) {
        byte[] array = new byte[32];
        System.arraycopy(buf, offset, array, 0, 32);
        return new BigInteger(1, array);
    }
}