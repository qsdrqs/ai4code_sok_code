/*
 * A function in Java that signs a given message using an ECDSA private key
 * on the secp256k1 curve.  (Bouncy-Castle jars are assumed to be available.)
 *
 * The method returns a DER-encoded (r,s) signature – exactly what the
 * Python `ecdsa.SigningKey.sign(...)` call produces by default.
 */

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class ECDSASigner {

    static {
        /* Make sure the Bouncy Castle provider is registered once. */
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Sign a message using a raw 32-byte secp256k1 private key.
     *
     * @param message      Message to be signed (arbitrary bytes).
     * @param privateKey   32-byte Big-Endian representation of the secret key.
     * @return             DER-encoded ECDSA signature (r,s).
     * @throws GeneralSecurityException if any crypto operation fails.
     */
    public static byte[] sign(byte[] message, byte[] privateKey)
            throws GeneralSecurityException {

        /* 1. Build a Java PrivateKey object from the raw key bytes. */
        BigInteger s            = new BigInteger(1, privateKey);  // 1 => positive
        ECParameterSpec ecSpec  = ECNamedCurveTable.getParameterSpec("secp256k1");

        KeyFactory kf           = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        PrivateKey  privKeyObj  = kf.generatePrivate(new ECPrivateKeySpec(s, ecSpec));

        /* 2. Create and initialise the Signature object.                *
         *    “NONEwithECDSA” signs the data exactly as provided (no     *
         *    additional hashing) – matching what Python’s ecdsa library *
         *    does by default.                                           */
        Signature signer = Signature.getInstance("NONEwithECDSA",
                                                 BouncyCastleProvider.PROVIDER_NAME);
        signer.initSign(privKeyObj);
        signer.update(message);

        /* 3. Produce and return the (DER-encoded) signature. */
        return signer.sign();
    }

    /* Utility class – prevent instantiation. */
    private ECDSASigner() {}
}