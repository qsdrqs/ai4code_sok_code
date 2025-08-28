import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;

public class Secp256k1Sha3Demo {

    /* --- curve / domain constants --------------------------------------- */
    private static final ECParameterSpec CURVE_SPEC =
            ECNamedCurveTable.getParameterSpec("secp256k1");

    private static final ECDomainParameters DOMAIN =
            new ECDomainParameters(
                    CURVE_SPEC.getCurve(),
                    CURVE_SPEC.getG(),
                    CURVE_SPEC.getN(),
                    CURVE_SPEC.getH());

    private static final SecureRandom RNG = new SecureRandom();

    /* --- utilities ------------------------------------------------------- */
    /** SHA3-256 of a UTF-8 message → positive BigInteger (same as Python code). */
    private static BigInteger sha3_256Hash(String msg) {
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);

        SHA3Digest digest = new SHA3Digest(256);
        digest.update(data, 0, data.length);

        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        return new BigInteger(1, hash); // 1 = positive
    }

    /* --- signing / verifying -------------------------------------------- */
    /** Sign the message with the given private key (returns {r, s}). */
    private static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {

        BigInteger msgHash = sha3_256Hash(msg);

        ECPrivateKeyParameters privParams =
                new ECPrivateKeyParameters(privKey, DOMAIN);

        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ParametersWithRandom(privParams, RNG));

        return signer.generateSignature(msgHash.toByteArray());
    }

    /** Verify signature (convenience overload that derives the public key
        from the passed private key).  Equivalent to the Python example.     */
    private static boolean verifyECDSAsecp256k1(
            String msg,
            BigInteger[] signature,
            BigInteger privKey) {

        BigInteger msgHash = sha3_256Hash(msg);

        // Q = G * d   (public point)
        ECDSASigner verifier = new ECDSASigner();
        verifier.init(false,
                new org.bouncycastle.crypto.params.ECPublicKeyParameters(
                        DOMAIN.getG().multiply(privKey), DOMAIN));

        return verifier.verifySignature(msgHash.toByteArray(),
                                        signature[0], signature[1]);
    }

    /* --- demonstration --------------------------------------------------- */
    public static void main(String[] args) {

        String msg = "Message for ECDSA signing";

        /* create a random private key  1 ≤ d < n  */
        BigInteger n = DOMAIN.getN();
        BigInteger privKey;
        do {
            privKey = new BigInteger(n.bitLength(), RNG);
        } while (privKey.signum() == 0 || privKey.compareTo(n) >= 0);

        /* sign ---------------------------------------------------------------- */
        BigInteger[] sig = signECDSAsecp256k1(msg, privKey);

        /* output -------------------------------------------------------------- */
        System.out.println("Message:      " + msg);
        System.out.println("Private key:  0x" + privKey.toString(16));
        System.out.println("Signature:    r=0x" + sig[0].toString(16)
                         + ", s=0x" + sig[1].toString(16));

        /* optional verification (not in original Python print-out) ------------ */
        System.out.println("Verified:     "
                + verifyECDSAsecp256k1(msg, sig, privKey));
    }
}