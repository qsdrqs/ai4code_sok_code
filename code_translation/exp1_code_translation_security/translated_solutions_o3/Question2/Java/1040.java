/*
 *  Sign message – ECDSA / secp256k1
 *
 *  (Dependencies such as Bouncy Castle are assumed to be available on the
 *  class-path, exactly as the Python version implicitly relied on the
 *  “ecdsa” package.)
 */

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/** Utility that reproduces the behaviour of the Python code. */
public final class EcdsaSigner {

    static {
        /* Make sure the Bouncy Castle provider is registered. */
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs the supplied message with a raw 32-byte secp256k1 private key.
     *
     * @param message     Arbitrary data to be signed (exactly the bytes given
     *                    are hashed and signed, just like in the Python code).
     * @param privateKey  32-byte big-endian representation of the secret scalar.
     * @return            DER-encoded ECDSA signature.
     * @throws GeneralSecurityException if anything goes wrong.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey)
            throws GeneralSecurityException {

        /* 1. Turn the raw 32-byte key into a Java PrivateKey object. */
        KeyFactory keyFactory   = KeyFactory.getInstance("EC", "BC");
        ECParameterSpec ecSpec  = ECNamedCurveTable.getParameterSpec("secp256k1");
        BigInteger d            = new BigInteger(1, privateKey);   // private scalar
        ECPrivateKeySpec privKS = new ECPrivateKeySpec(d, ecSpec);
        PrivateKey privKey      = keyFactory.generatePrivate(privKS);

        /* 2. Sign: Python’s ecdsa.SigningKey.sign() hashes with SHA-1 by default. */
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(privKey);
        signer.update(message);
        return signer.sign();                      // DER encoded signature bytes
    }

    /* -------------------------------------------------------------------- */
    /* Optional demo / sanity-check.                                        */
    /* -------------------------------------------------------------------- */
    public static void main(String[] args) throws Exception {
        // Example 32-byte private key (DON'T USE IN PRODUCTION)
        byte[] priv = new byte[32];
        priv[31] = 1;  // … just for demo: scalar = 1

        byte[] msg  = "Hello, ECDSA!".getBytes("UTF-8");
        byte[] sig  = signMessage(msg, priv);

        System.out.println("Signature (hex): " + bytesToHex(sig));
    }

    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}