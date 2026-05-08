import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;

public class SignMessage {

    /**
     * Function signMessage
     *
     * Input: message and ECDSA key
     * Output: message signed using the provided key
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Get SECP256k1 curve parameters
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Create a new ECDSA key from the private key bytes
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, spec);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey key = keyFactory.generatePrivate(privateKeySpec);

        // Sign the message (Python's ecdsa library uses SHA-1 as the default hash function)
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(key);
        signer.update(message);
        byte[] derSignature = signer.sign();

        // Convert DER-encoded signature to raw r||s format (Python ecdsa's default output)
        return derToRaw(derSignature, 32);
    }

    private static byte[] derToRaw(byte[] derSignature, int keySize) {
        ASN1Sequence sequence = ASN1Sequence.getInstance(derSignature);
        BigInteger r = ((ASN1Integer) sequence.getObjectAt(0)).getValue();
        BigInteger s = ((ASN1Integer) sequence.getObjectAt(1)).getValue();

        byte[] rawSignature = new byte[keySize * 2];
        System.arraycopy(bigIntegerToBytes(r, keySize), 0, rawSignature, 0, keySize);
        System.arraycopy(bigIntegerToBytes(s, keySize), 0, rawSignature, keySize, keySize);

        return rawSignature;
    }

    private static byte[] bigIntegerToBytes(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        byte[] result = new byte[length];
        if (bytes.length <= length) {
            System.arraycopy(bytes, 0, result, length - bytes.length, bytes.length);
        } else {
            // Remove the sign byte
            System.arraycopy(bytes, bytes.length - length, result, 0, length);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        byte[] signature = signMessage(
                "hello".getBytes(StandardCharsets.UTF_8),
                "qwertyuiopasdfghjklzxcvbnmqwerty".getBytes(StandardCharsets.UTF_8)
        );
        System.out.println(Arrays.toString(signature));
    }
}