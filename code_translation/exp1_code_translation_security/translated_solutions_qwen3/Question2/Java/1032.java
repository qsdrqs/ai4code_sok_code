import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.params.ParametersWithRandom;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Arrays;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        String message = "Hello, world!";
        String privateKeyHex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
        byte[] privateKey = hexStringToByteArray(privateKeyHex);
        byte[] signature = signMessage(message, privateKey);
        System.out.println(bytesToHex(signature));
    }

    public static byte[] signMessage(String message, byte[] privateKey) throws Exception {
        // Hash the message
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        // Get the curve parameters
        ECDomainParameters ecParams = (ECDomainParameters) org.bouncycastle.jce.provider.BouncyCastleProvider.getECNamedCurveByName("secp256k1");

        // Create the private key
        BigInteger d = new BigInteger(1, privateKey);
        ECPrivateKeyParameters key = new ECPrivateKeyParameters(d, ecParams);

        // Create the signer
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ParametersWithRandom(key));
        BigInteger[] sig = signer.generateSignature(hash);

        // Convert r and s to 32-byte arrays
        byte[] r = to32ByteArray(sig[0]);
        byte[] s = to32ByteArray(sig[1]);

        // Concatenate r and s
        byte[] signature = new byte[64];
        System.arraycopy(r, 0, signature, 0, 32);
        System.arraycopy(s, 0, signature, 32, 32);

        return signature;
    }

    private static byte[] to32ByteArray(BigInteger bi) {
        byte[] bytes = bi.toByteArray();
        if (bytes.length == 32) {
            return bytes;
        } else if (bytes.length < 32) {
            byte[] padded = new byte[32];
            Arrays.fill(padded, (byte) 0);
            System.arraycopy(bytes, 0, padded, 32 - bytes.length, bytes.length);
            return padded;
        } else {
            // Take last 32 bytes
            byte[] last32 = new byte[32];
            System.arraycopy(bytes, bytes.length - 32, last32, 0, 32);
            return last32;
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}