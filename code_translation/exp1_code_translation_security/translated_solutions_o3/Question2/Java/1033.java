import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.GeneralSecurityException;
import java.security.spec.ECGenParameterSpec;

/**
 * Utility class for ECDSA operations.
 */
public final class ECDSAUtils {

    private ECDSAUtils() { /* utility class – no instances */ }

    /**
     * Signs a given message using a freshly generated ECDSA private key that
     * resides on the supplied elliptic-curve.
     *
     * NOTE:  Just like the original Python snippet, the {@code key} argument is
     *        treated as the name/identifier of the curve and NOT an already
     *        existing private key.  A new key pair is produced every time
     *        the method is called.
     *
     * @param message   the byte array containing the data to be signed
     * @param curveName the standard name of the curve
     *                 (e.g. "secp256r1", "secp256k1", "prime256v1", …)
     *
     * @return the DER-encoded ECDSA signature for {@code message}
     *
     * @throws GeneralSecurityException if something goes wrong while
     *                                  generating the key or creating
     *                                  the signature
     */
    public static byte[] signMessage(byte[] message, String curveName)
            throws GeneralSecurityException {

        /* ----------------------------------------------------------
           1. Generate a random ECDSA key pair on the requested curve
           ---------------------------------------------------------- */
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC"); // “BC” = Bouncy Castle provider
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(curveName);
        keyGen.initialize(ecSpec, new SecureRandom());

        KeyPair keyPair = keyGen.generateKeyPair(); // contains both private & public part

        /* ----------------------------------------------------------
           2. Sign the message with the private key
           ---------------------------------------------------------- */
        Signature ecdsaSigner = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSigner.initSign(keyPair.getPrivate());
        ecdsaSigner.update(message);

        return ecdsaSigner.sign(); // DER-encoded signature
    }
}