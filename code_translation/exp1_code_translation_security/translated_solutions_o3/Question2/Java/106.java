import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

/**
 * Demonstrates signing / verifying a message with ECDSA (secp256k1) in Java.
 *
 * NOTE: The Bouncy Castle provider (bcprov-* jar) must be on the class-path.
 */
public class ECDSADemo {

    /**
     * Signs a given message using the supplied private key.
     *
     * @param message message to sign
     * @param key     ECDSA private key
     * @return        ECDSA signature bytes
     */
    public static byte[] sign(byte[] message, PrivateKey key) throws Exception {
        Signature signer = Signature.getInstance("NONEwithECDSA", "BC"); // raw ECDSA
        signer.initSign(key);
        signer.update(message);
        return signer.sign();
    }

    /**
     * Verifies a signature for a given message.
     *
     * @param message   the original message
     * @param signature the signature to verify
     * @param key       ECDSA public key
     * @return          true if the signature is valid, false otherwise
     */
    public static boolean verify(byte[] message,
                                 byte[] signature,
                                 PublicKey key) throws Exception {
        Signature verifier = Signature.getInstance("NONEwithECDSA", "BC"); // raw ECDSA
        verifier.initVerify(key);
        verifier.update(message);
        return verifier.verify(signature);
    }

    public static void main(String[] args) throws Exception {

        /* ------------------------------------------------------------------
         * 1. Make sure the Bouncy Castle provider is registered
         * ------------------------------------------------------------------ */
        Security.addProvider(new BouncyCastleProvider());

        /* ------------------------------------------------------------------
         * 2. Generate an ECDSA key-pair on the secp256k1 curve
         * ------------------------------------------------------------------ */
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(ecSpec, new SecureRandom());

        KeyPair pair   = kpg.generateKeyPair();
        PrivateKey sk  = pair.getPrivate();
        PublicKey  vk  = pair.getPublic();

        /* ------------------------------------------------------------------
         * 3. Sign / verify the message
         * ------------------------------------------------------------------ */
        byte[] message        = "helloworld".getBytes("UTF-8");
        byte[] signedMessage  = sign(message, sk);

        System.out.println(verify(message, signedMessage, vk)); // prints: true
    }
}