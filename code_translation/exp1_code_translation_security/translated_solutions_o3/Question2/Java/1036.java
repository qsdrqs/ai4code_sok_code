import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class EcdsaExample {

    /**
     * Signs a UTF-8 message string with a raw 32-byte SECP-256K1 private key.
     *
     * The method reproduces the behaviour of the Python snippet:
     *   1) SHA-256 hash the message
     *   2) Deterministically sign that digest (RFC-6979 / HMAC-DSA-K)
     *   3) Return DER-encoded signature in hexadecimal
     */
    public static String signMessage(String message, byte[] privateKeyBytes) throws Exception {

        /* ------------------------------------------------------------------ */
        /* 1. SHA-256 of the message                                          */
        /* ------------------------------------------------------------------ */
        byte[] msgBytes  = message.getBytes(StandardCharsets.UTF_8);
        SHA256Digest sha = new SHA256Digest();
        sha.update(msgBytes, 0, msgBytes.length);
        byte[] messageHash = new byte[sha.getDigestSize()];
        sha.doFinal(messageHash, 0);                       // 32-byte digest

        /* ------------------------------------------------------------------ */
        /* 2. Build EC private-key params for secp256k1                       */
        /* ------------------------------------------------------------------ */
        X9ECParameters   curve   = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domain = new ECDomainParameters(
                curve.getCurve(), curve.getG(), curve.getN(), curve.getH());

        BigInteger          d          = new BigInteger(1, privateKeyBytes);
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(d, domain);

        /* ------------------------------------------------------------------ */
        /* 3. Deterministic ECDSA signature (same as python-ecdsa sign_digest)*/
        /* ------------------------------------------------------------------ */
        ECDSASigner signer = new ECDSASigner(
                new HMacDSAKCalculator(new SHA256Digest())); // deterministic k
        signer.init(true, privKey);

        BigInteger[] sig = signer.generateSignature(messageHash); // {r, s}

        /* ------------------------------------------------------------------ */
        /* 4. Pack (r,s) into DER exactly like ecdsa.util.sigencode_der       */
        /* ------------------------------------------------------------------ */
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(sig[0]));
        v.add(new ASN1Integer(sig[1]));
        byte[] derSignature = new DERSequence(v).getEncoded();

        /* 5. Return hex string                                               */
        return Hex.toHexString(derSignature);
    }

    /* Simple test driver */
    public static void main(String[] args) throws Exception {

        /* Make sure BC provider is available (optional if already registered) */
        Security.addProvider(new BouncyCastleProvider());

        String privateKeyHex =
                "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
        byte[] privateKey = Hex.decode(privateKeyHex);

        String message   = "Hello";
        String signature = signMessage(message, privateKey);

        System.out.println(signature);
    }
}