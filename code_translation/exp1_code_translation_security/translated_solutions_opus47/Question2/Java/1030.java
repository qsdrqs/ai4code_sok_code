// Maven dependency:
// <dependency>
//     <groupId>org.bouncycastle</groupId>
//     <artifactId>bcprov-jdk18on</artifactId>
//     <version>1.77</version>
// </dependency>

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class ECDSASecp256k1 {

    // Curve parameters for secp256k1
    private static final X9ECParameters CURVE_PARAMS = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters DOMAIN_PARAMS = new ECDomainParameters(
            CURVE_PARAMS.getCurve(),
            CURVE_PARAMS.getG(),
            CURVE_PARAMS.getN(),
            CURVE_PARAMS.getH()
    );
    private static final SecureRandom RANDOM = new SecureRandom();

    public static BigInteger sha3_256Hash(String msg) {
        SHA3Digest digest = new SHA3Digest(256);
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        digest.update(msgBytes, 0, msgBytes.length);
        byte[] hashBytes = new byte[digest.getDigestSize()];
        digest.doFinal(hashBytes, 0);
        return new BigInteger(1, hashBytes);
    }

    public static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        // Use HMAC-SHA256 based deterministic k generation (RFC 6979), matches pycoin default
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privKey, DOMAIN_PARAMS);
        signer.init(true, privKeyParams);
        return signer.generateSignature(toFixedLengthBytes(msgHash, 32));
    }

    public static boolean verifyECDSAsecp256k1(String msg, BigInteger[] signature, ECPoint pubKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        ECDSASigner verifier = new ECDSASigner();
        ECPublicKeyParameters pubKeyParams = new ECPublicKeyParameters(pubKey, DOMAIN_PARAMS);
        verifier.init(false, pubKeyParams);
        return verifier.verifySignature(toFixedLengthBytes(msgHash, 32), signature[0], signature[1]);
    }

    private static byte[] toFixedLengthBytes(BigInteger value, int length) {
        byte[] result = new byte[length];
        byte[] bytes = value.toByteArray();
        if (bytes.length <= length) {
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        } else {
            // Trim the leading zero byte added by BigInteger for the sign bit
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        }
        return result;
    }

    public static void main(String[] args) {
        // ECDSA sign message (using the curve secp256k1 + SHA3-256)
        String msg = "Message for ECDSA signing";

        // signing key, must be integer in [1, n-1]
        BigInteger n = DOMAIN_PARAMS.getN();
        BigInteger privKey;
        do {
            privKey = new BigInteger(n.bitLength(), RANDOM);
        } while (privKey.signum() == 0 || privKey.compareTo(n) >= 0);

        BigInteger[] signature = signECDSAsecp256k1(msg, privKey);

        System.out.println("Message: " + msg);
        System.out.println("Private key: 0x" + privKey.toString(16));
        System.out.println("Signature: r=0x" + signature[0].toString(16) + ", s=0x" + signature[1].toString(16));
    }
}