import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

/**
 * sign message ecdsa
 */
public class SignMessageEcdsa {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sign message
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        BigInteger privKeyBigInt = new BigInteger(1, privateKey);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privKeyBigInt, ecSpec);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privKey = keyFactory.generatePrivate(privateKeySpec);

        // Python's ecdsa library uses SHA-1 as the default hash function for sign()
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(privKey);
        signature.update(message);
        byte[] derSignature = signature.sign();

        // Convert DER signature to r||s format (64 bytes) to match Python ecdsa's default output
        return derToRs(derSignature);
    }

    private static byte[] derToRs(byte[] derSignature) throws Exception {
        try (ASN1InputStream asn1 = new ASN1InputStream(new ByteArrayInputStream(derSignature))) {
            ASN1Sequence seq = (ASN1Sequence) asn1.readObject();
            BigInteger r = ((ASN1Integer) seq.getObjectAt(0)).getValue();
            BigInteger s = ((ASN1Integer) seq.getObjectAt(1)).getValue();

            byte[] rBytes = bigIntToFixedLength(r, 32);
            byte[] sBytes = bigIntToFixedLength(s, 32);

            byte[] result = new byte[64];
            System.arraycopy(rBytes, 0, result, 0, 32);
            System.arraycopy(sBytes, 0, result, 32, 32);

            return result;
        }
    }

    private static byte[] bigIntToFixedLength(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == length) {
            return bytes;
        }
        byte[] result = new byte[length];
        if (bytes.length < length) {
            // Left-pad with zeros
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        } else {
            // Remove leading sign byte
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        }
        return result;
    }
}