import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;

/**
 * Equivalent of the Python helpers shown in the question,
 * implemented with Bouncy-Castle and the NIST P-256 curve.
 *
 * The code assumes
 *   • signingKeyRaw is a 32-byte private scalar (big-endian)
 *   • verifyingKeyRaw is a 64-byte X‖Y public point (big-endian, uncompressed)
 */
public final class EcdsaUtil {

    // Register Bouncy-Castle once
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // Curve parameters for NIST P-256 (a.k.a. secp256r1)
    private static final ECParameterSpec CURVE =
            ECNamedCurveTable.getParameterSpec("secp256r1");

    private EcdsaUtil() { /* no instances */ }

    /**
     * Generates an ECDSA/NIST-256 signature of the supplied message.
     *
     * @param message        message to be signed
     * @param signingKeyRaw  32-byte private key
     * @return DER-encoded ECDSA signature
     */
    public static byte[] signNist256(byte[] message,
                                     byte[] signingKeyRaw) throws GeneralSecurityException {

        KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
        BigInteger d   = new BigInteger(1, signingKeyRaw);          // private scalar d
        PrivateKey pk  = kf.generatePrivate(new ECPrivateKeySpec(d, CURVE));

        Signature sig = Signature.getInstance("SHA256withECDSA", "BC");
        sig.initSign(pk);
        sig.update(message);
        return sig.sign();
    }

    /**
     * Verifies an ECDSA/NIST-256 signature.
     *
     * @param message          original message
     * @param signature        DER-encoded signature to verify
     * @param verifyingKeyRaw  64-byte X‖Y public key
     * @return true if valid, false otherwise
     */
    public static boolean verifyNist256(byte[] message,
                                        byte[] signature,
                                        byte[] verifyingKeyRaw) {

        try {
            if (verifyingKeyRaw.length != 64) {
                throw new IllegalArgumentException("Public key must be 64 raw bytes (x||y).");
            }

            // Split X and Y coordinates
            byte[] xBytes = Arrays.copyOfRange(verifyingKeyRaw, 0, 32);
            byte[] yBytes = Arrays.copyOfRange(verifyingKeyRaw, 32, 64);

            BigInteger x = new BigInteger(1, xBytes);
            BigInteger y = new BigInteger(1, yBytes);

            ECPoint q = CURVE.getCurve().createPoint(x, y);
            KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
            PublicKey  pub = kf.generatePublic(new ECPublicKeySpec(q, CURVE));

            Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
            verifier.initVerify(pub);
            verifier.update(message);

            return verifier.verify(signature);

        } catch (GeneralSecurityException | IllegalArgumentException e) {
            // Any error (invalid key, bad signature, etc.) is treated as failure
            return false;
        }
    }
}