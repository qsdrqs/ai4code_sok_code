/*
 * Functions that sign a given message using a given ECDSA signing key
 *
 *  – Dependencies –
 *    1. Bouncy Castle provider   (e.g.  “bcprov-jdk15on-*.jar”)
 *       https://www.bouncycastle.org/latest_releases.html
 *
 *    2. A valid EC private key (java.security.PrivateKey) that
 *       corresponds to the curve you are working with (secp256k1,
 *       secp256r1, …).  How you load/build that key is outside the
 *       scope of this snippet.
 */

import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class ECDSASigner {

    /* ------------------------------------------------------------------ */
    /*  Static initialisation: register Bouncy Castle once at class‐load   */
    /* ------------------------------------------------------------------ */
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Signs a given message using the supplied ECDSA private key.
     *
     * Behaviour is identical to the original Python code:
     *   1. Compute SHA-256 of the raw message.
     *   2. Create an ECDSA signature over that hash.
     *   3. Return the signature in DER format (r | s).
     *
     * @param message     the message to be signed (arbitrary bytes)
     * @param signingKey  an EC private key that matches the curve used
     * @return            DER-encoded ECDSA signature (r,s)
     * @throws GeneralSecurityException if anything goes wrong
     */
    public static byte[] signMessage(byte[] message,
                                     PrivateKey signingKey)
            throws GeneralSecurityException {

        /* ---------------------------------------------------------- */
        /*  1. Hash the message                                       */
        /* ---------------------------------------------------------- */
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = sha256.digest(message);

        /* ---------------------------------------------------------- */
        /*  2. Sign the hash                                          */
        /*     We use “NONEwithECDSA” because we already hashed.      */
        /*     Bouncy Castle guarantees DER output for Signature.sign */
        /* ---------------------------------------------------------- */
        Signature ecdsa = Signature.getInstance("NONEwithECDSA",
                                                BouncyCastleProvider.PROVIDER_NAME);
        ecdsa.initSign(signingKey);
        ecdsa.update(messageHash);

        return ecdsa.sign();   // DER-encoded (r,s)
    }

    /* -------------------------------------------------------------- */
    /*  (Optional) Small example to illustrate usage                  */
    /* -------------------------------------------------------------- */
    public static void main(String[] args) throws Exception {
        // *** This is just demo code.  In real usage you will load
        //     or generate an EC key appropriate for your application. ***

        // 1. Generate an ephemeral P-256 key pair for the demo
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair kp = kpg.generateKeyPair();

        // 2. Message we want to sign
        byte[] message = "Hello, world!".getBytes("UTF-8");

        // 3. Produce signature
        byte[] sig = signMessage(message, kp.getPrivate());

        // 4. Display result (hex)
        System.out.printf("Signature (%d bytes): %s%n",
                          sig.length, bytesToHex(sig));
    }

    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}