import java.nio.charset.StandardCharsets;
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
 * Equivalent to:
 *     from ecdsa import SigningKey
 *     sk = SigningKey.generate()       # uses NIST192p
 *     vk = sk.verifying_key
 *
 *     def sign(msg, sk):
 *         return sk.sign(msg)
 *
 *     assert vk.verify(sign(b"message", sk), b"message")
 */
public class ECDSADemo {

    public static void main(String[] args) throws Exception {
        /* Make sure the Bouncy Castle provider is registered. */
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        /* ------------------------------------------------------------------
         * 1. Key-pair generation on NIST P-192 (a.k.a. secp192r1 / prime192v1)
         * ------------------------------------------------------------------ */
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("P-192");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        keyGen.initialize(ecSpec, new SecureRandom());

        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();   // Signing key
        PublicKey  vk = keyPair.getPublic();    // Verifying key

        /* ------------------------------------------------------------------
         * 2. Sign the message
         * ------------------------------------------------------------------ */
        byte[] msg = "message".getBytes(StandardCharsets.UTF_8);
        byte[] signature = sign(msg, sk);

        /* ------------------------------------------------------------------
         * 3. Verify the signature
         * ------------------------------------------------------------------ */
        Signature verifier = Signature.getInstance("SHA1withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update(msg);

        boolean ok = verifier.verify(signature);
        assert ok : "ECDSA signature failed to verify";

        System.out.println("Signature verified: " + ok);
    }

    /**
     * Sign an arbitrary byte-array message with the given private key.
     */
    private static byte[] sign(byte[] msg, PrivateKey sk) throws Exception {
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(sk);
        signer.update(msg);
        return signer.sign();
    }
}