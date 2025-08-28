import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public final class ECDSASigner {

    /**
     * Sign arbitrary data with an EC private key using the
     * SHA-256 with ECDSA signature scheme.
     *
     * @param privateKey  an EC private key (e.g. secp384r1)
     * @param data        data to be signed
     * @return            DER-encoded ECDSA signature (r‖s)
     * @throws GeneralSecurityException if a crypto error occurs
     */
    public static byte[] signString(PrivateKey privateKey, byte[] data)
            throws GeneralSecurityException {

        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(privateKey);
        signer.update(data);
        return signer.sign();          // DER-encoded (ASN.1) signature
    }


    /* ----------  Example usage / quick test  ---------- */

    public static void main(String[] args) throws Exception {
        // Generate an EC key on the NIST P-384 curve (secp384r1)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"), new SecureRandom());
        KeyPair kp = kpg.generateKeyPair();

        byte[] data = "hello world".getBytes(StandardCharsets.UTF_8);

        // Produce the signature
        byte[] sig = signString(kp.getPrivate(), data);
        System.out.println("Signature length  : " + sig.length + " bytes");

        // --- optional: verify to prove it worked ---
        Signature verifier = Signature.getInstance("SHA256withECDSA");
        verifier.initVerify(kp.getPublic());
        verifier.update(data);
        boolean ok = verifier.verify(sig);
        System.out.println("Signature valid   : " + ok);
    }
}