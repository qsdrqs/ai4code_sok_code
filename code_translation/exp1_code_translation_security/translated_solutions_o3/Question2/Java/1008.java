import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECPrivateKeySpec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

/**
 * Demonstrates how to sign a message with an ECDSA (secp256k1) private key.
 * <p>
 * NOTE:  The Python implementation (`ecdsa` package) signs with SHA-1 by
 * default, so we use "SHA1withECDSA" here for an identical result.
 */
public class EcdsaSigner {

    /**
     * Signs a UTF-8 message string using a secp256k1 private key whose raw
     * bytes are given by another UTF-8 string (exactly like the Python code).
     *
     * @param message     the message to sign
     * @param privateKey  32-byte (UTF-8) string holding the raw private key
     * @return            the DER-encoded signature bytes
     */
    public static byte[] signMessage(String message, String privateKey) throws Exception {

        /* --- build the PrivateKey object from raw bytes --- */
        byte[] privBytes = privateKey.getBytes(StandardCharsets.UTF_8);   // 32 bytes
        BigInteger d      = new BigInteger(1, privBytes);                 // secret exponent

        ECNamedCurveParameterSpec curveSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECPrivateKeySpec          keySpec   = new ECPrivateKeySpec(d, curveSpec);

        KeyFactory  kf       = KeyFactory.getInstance("ECDSA", "BC");     // Bouncy Castle provider
        PrivateKey  privKey  = kf.generatePrivate(keySpec);

        /* --- standard ECDSA signature (SHA-1 digest, like python-ecdsa default) --- */
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(privKey);
        signer.update(message.getBytes(StandardCharsets.UTF_8));

        return signer.sign();                                             // DER-encoded sig bytes
    }

    /* helper: hex-encode a byte array so we can read the output easily */
    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) sb.append(String.format("%02x", b & 0xFF));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        byte[] sig = signMessage("hello world", "12345678901234567890123456789012");
        System.out.println(toHex(sig));
    }
}