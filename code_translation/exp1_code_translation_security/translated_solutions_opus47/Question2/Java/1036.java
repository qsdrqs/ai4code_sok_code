import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SignMessage {

    public static String signMessage(String message, byte[] privateKey) throws Exception {
        // Hash the message
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        // Set up curve parameters for SECP256k1
        X9ECParameters curveParams = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(
                curveParams.getCurve(),
                curveParams.getG(),
                curveParams.getN(),
                curveParams.getH()
        );

        // Create private key parameters
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        ECPrivateKeyParameters privateKeyParams = new ECPrivateKeyParameters(privateKeyInt, domainParams);

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKeyParams);
        BigInteger[] signature = signer.generateSignature(messageHash);

        // Encode signature as DER (SEQUENCE of two INTEGERs: r and s)
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(signature[0])); // r
        v.add(new ASN1Integer(signature[1])); // s
        byte[] derSignature = new DERSequence(v).getEncoded();

        // Return hex string
        return bytesToHex(derSignature);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            String h = Integer.toHexString(0xff & b);
            if (h.length() == 1) hex.append('0');
            hex.append(h);
        }
        return hex.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        // Test the function
        byte[] privateKey = hexToBytes("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109");
        String message = "Hello";
        String signature = signMessage(message, privateKey);
        System.out.println(signature);
    }
}